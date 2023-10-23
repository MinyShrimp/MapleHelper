package git.shrimp.maple_helper.ability.service

import git.shrimp.maple_helper.ability.data.AbilityNumericRepository
import git.shrimp.maple_helper.ability.data.AbilityOptionRepository
import git.shrimp.maple_helper.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.ability.dto.AbilityResult
import git.shrimp.maple_helper.ability.dto.SimulationOption
import git.shrimp.maple_helper.ability.dto.SimulationResult
import git.shrimp.maple_helper.ability.dto.TargetDto
import git.shrimp.maple_helper.ability.model.AbilityMode
import git.shrimp.maple_helper.ability.model.AbilityNumeric
import git.shrimp.maple_helper.ability.model.AbilityOption
import git.shrimp.maple_helper.ability.model.AbilityWeight
import git.shrimp.maple_helper.global.model.OptionLevel
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
        return when(mode) {
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
        mode: AbilityMode
    ): AbilityResult {
        return this.getResult(mainLevel, mode)
    }

    private fun getSubOption(
        mainLevel: OptionLevel,
        mode: AbilityMode,
        options: List<AbilityOption>
    ): AbilityResult {
        val random = this.getRandom(100)

        return when(mainLevel) {
            OptionLevel.LEGENDARY -> if(random < 15) { this.getResult(OptionLevel.UNIQUE, mode, options) } else { this.getResult(OptionLevel.EPIC, mode, options) }
            OptionLevel.UNIQUE -> if(random < 30) { this.getResult(OptionLevel.EPIC, mode, options) } else { this.getResult(OptionLevel.RARE, mode, options) }
            else -> this.getResult(OptionLevel.RARE, mode, options)
        }
    }

    fun getOption(
        mainLevel: OptionLevel = OptionLevel.LEGENDARY,
        mode: AbilityMode = AbilityMode.NORMAL
    ): List<AbilityResult> {
        val result = mutableListOf<AbilityResult>()

        result.add(this.getMainOption(mainLevel, mode))
        result.add(this.getSubOption(mainLevel, mode, result))
        result.add(this.getSubOption(mainLevel, mode, result))

        return result.toList()
    }

    private suspend fun simulation(
        target: AbilityResult,
        option: SimulationOption
    ): List<SimulationResult> {
        val simulationResults = mutableListOf<SimulationResult>()
        for(index in 0 until option.maxCount) {
            run loop@{
                for(c in 0 until 100000) {
                    val results = this.getOption(option.mainLevel, option.mode)
                    if (results.contains(target)) {
                        simulationResults.add(SimulationResult(c, results))
                        return@loop
                    }
                }
                simulationResults.add(SimulationResult(option.maxCount, listOf()))
            }
        }

        return simulationResults.toList()
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun simulation(
        targetDto: TargetDto,
        option: SimulationOption
    ): List<SimulationResult> {
        val targetBase = this.abilityOptionRepository.get(targetDto.id)
        val target = AbilityResult(
            id = targetBase.id,
            name = targetBase.name,
            level = targetDto.level,
            numeric = targetDto.numeric,
        )

        val diff = option.maxCount / 1000
        return GlobalScope.async {
            val simulationResults = (0 until 1000).map { async { simulation(target, option.copy(maxCount = diff)) } }
            simulationResults.flatMap { it.await() }
        }.await()
    }
}
