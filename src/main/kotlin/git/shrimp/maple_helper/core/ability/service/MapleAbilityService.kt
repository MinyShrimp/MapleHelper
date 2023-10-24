package git.shrimp.maple_helper.core.ability.service

import git.shrimp.maple_helper.core.ability.data.AbilityNumericRepository
import git.shrimp.maple_helper.core.ability.data.AbilityOptionRepository
import git.shrimp.maple_helper.core.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.core.ability.dto.AbilityResult
import git.shrimp.maple_helper.core.ability.dto.SimulationOption
import git.shrimp.maple_helper.core.ability.dto.SimulationResult
import git.shrimp.maple_helper.core.ability.dto.TargetDto
import git.shrimp.maple_helper.core.ability.model.AbilityMode
import git.shrimp.maple_helper.core.ability.model.AbilityNumeric
import git.shrimp.maple_helper.core.ability.model.AbilityOption
import git.shrimp.maple_helper.core.ability.model.AbilityWeight
import git.shrimp.maple_helper.core.global.model.OptionLevel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.springframework.stereotype.Service

@Service
class MapleAbilityService(
    private val abilityOptionRepository: AbilityOptionRepository,
    private val abilityWeightRepository: AbilityWeightRepository,
    private val abilityNumericRepository: AbilityNumericRepository
) {
    private fun getRandom(
        maxNumber: Int
    ): Int {
        return (0 until maxNumber).random()
    }

    private fun getRandomWeight(
        weights: List<AbilityWeight>
    ): AbilityWeight {
        val totalWeight = weights.sumOf { it.weight }
        val random = this.getRandom(totalWeight)

        var sum = 0
        return weights.find {
            sum += it.weight
            sum >= random
        }!!
    }

    private fun getOption(
        weights: List<AbilityWeight>
    ): AbilityOption {
        return this.getRandomWeight(weights).option
    }

    private fun getNumeric(
        numerics: List<AbilityNumeric>,
        mode: AbilityMode
    ): Array<Int> {
        return when (mode) {
            AbilityMode.NORMAL -> (this.getRandomWeight(numerics) as AbilityNumeric).numeric
            AbilityMode.MIRACLE -> numerics.last().numeric
        }
    }

    private fun getResult(
        level: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityOption> = listOf()
    ): AbilityResult {
        val weights = this.abilityWeightRepository.getItems(level, withoutOptions)
        val option = this.getOption(weights.shuffled())

        val numerics = this.abilityNumericRepository.getItems(option.id, level)
        val numeric = this.getNumeric(numerics, mode)

        return AbilityResult(
            id = option.id,
            name = option.name,
            level = level,
            numeric = numeric,
        )
    }

    private fun getMainOption(
        mainLevel: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityOption>
    ): AbilityResult {
        return this.getResult(mainLevel, mode, withoutOptions)
    }

    private fun getSubOption(
        mainLevel: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityOption>
    ): AbilityResult {
        val random = this.getRandom(100)

        return when (mainLevel) {
            OptionLevel.LEGENDARY -> if (random < 15) {
                this.getResult(OptionLevel.UNIQUE, mode, withoutOptions)
            } else {
                this.getResult(OptionLevel.EPIC, mode, withoutOptions)
            }

            OptionLevel.UNIQUE -> if (random < 30) {
                this.getResult(OptionLevel.EPIC, mode, withoutOptions)
            } else {
                this.getResult(OptionLevel.RARE, mode, withoutOptions)
            }

            else -> this.getResult(OptionLevel.RARE, mode, withoutOptions)
        }
    }

    fun getOption(
        mainLevel: OptionLevel = OptionLevel.LEGENDARY,
        mode: AbilityMode = AbilityMode.NORMAL,
        locks: List<AbilityResult> = listOf()
    ): List<AbilityResult> {
        val result = mutableListOf<AbilityResult>()
        result.addAll(locks)

        if (locks.find { it.level == mainLevel } == null) {
            result.add(this.getMainOption(mainLevel, mode, result))
        }

        if (locks.find { it.level != mainLevel } == null) {
            result.add(this.getSubOption(mainLevel, mode, result))
            result.add(this.getSubOption(mainLevel, mode, result))
        } else {
            result.add(this.getSubOption(mainLevel, mode, result))
        }

        result.sortByDescending { it.level.ordinal }
        return result.toList()
    }

    private suspend fun simulate(
        option: SimulationOption,
        targets: List<AbilityResult>,
        locks: List<AbilityResult>
    ): List<SimulationResult> {
        val simulationResults = mutableListOf<SimulationResult>()
        for (index in 0 until option.maxCount) {
            run loop@{
                for (c in 0 until 100000) {
                    val results = this.getOption(option.mainLevel, option.mode, locks)
                    if (results.any { targets.contains(it) }) {
                        simulationResults.add(SimulationResult(c, results))
                        return@loop
                    }
                }
                simulationResults.add(SimulationResult(option.maxCount, listOf()))
            }
        }

        return simulationResults.toList()
    }

    private fun convertTargetDtoToAbilityResult(
        targetDto: TargetDto
    ): AbilityResult {
        val option = this.abilityOptionRepository.get(targetDto.id)

        return AbilityResult(
            id = option.id,
            name = option.name,
            level = targetDto.level,
            numeric = targetDto.numeric,
        )
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun simulation(
        option: SimulationOption,
        targetDtoList: List<TargetDto>,
        lockDtoList: List<TargetDto> = listOf()
    ): List<SimulationResult> {
        if (lockDtoList.count() > 2) {
            throw Exception("Lock count must be less than 2")
        }

        val targets = targetDtoList.map { this.convertTargetDtoToAbilityResult(it) }
        val locks = lockDtoList.map { this.convertTargetDtoToAbilityResult(it) }

        val batch = 100
        val diff = option.maxCount / batch
        return GlobalScope.async {
            val simulationResults = (0 until batch).map { async { simulate(option.copy(maxCount = diff), targets, locks) } }
            simulationResults.flatMap { it.await() }
        }.await()
    }
}
