package git.shrimp.maple_helper_core.ability.service

import git.shrimp.maple_helper_core.ability.dto.OptionDto
import git.shrimp.maple_helper_core.ability.model.*
import git.shrimp.maple_helper_core.ability.repository.*
import git.shrimp.maple_helper_core.global.model.OptionLevel
import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MapleAbilityService(
    private val abilityOptionRepository: AbilityOptionRepository,
    private val abilityWeightRepository: AbilityWeightRepository,
    private val abilityNumericRepository: AbilityNumericRepository,
    private val abilityResultRepository: AbilityResultRepository,
    private val abilityResultEntryRepository: AbilityResultEntryRepository
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

    private fun getOptionId(
        weights: List<AbilityWeight>
    ): Int {
        return this.getRandomWeight(weights).optionId
    }

    private fun getNumeric(
        numerics: List<AbilityNumeric>,
        mode: AbilityMode
    ): List<Int> {
        return when (mode) {
            AbilityMode.NORMAL -> (this.getRandomWeight(numerics) as AbilityNumeric).numerics
            AbilityMode.MIRACLE -> numerics.last().numerics
        }
    }

    private suspend fun getResult(
        level: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityResultEntry> = listOf()
    ): AbilityResultEntry {
        val withoutOptionIds = withoutOptions.map { it.optionId }
        val weights = withContext(Dispatchers.IO) {
            abilityWeightRepository.findAllByLevel(level)
        }.filterNot { withoutOptionIds.contains(it.optionId) }
        val optionId = this.getOptionId(weights.shuffled())

        val numerics = withContext(Dispatchers.IO) {
            abilityNumericRepository.findAllByOptionIdAndLevel(optionId, level)
        }
        val numeric = this.getNumeric(numerics, mode)

        val option = withContext(Dispatchers.IO) {
            abilityOptionRepository.findById(optionId)
        }.orElseThrow()
        return AbilityResultEntry(
            option = option,
            level = level,
            numerics = numeric
        )
    }

    private suspend fun getMainOption(
        mainLevel: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityResultEntry>
    ): AbilityResultEntry {
        return this.getResult(mainLevel, mode, withoutOptions)
    }

    private suspend fun getSubOption(
        mainLevel: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityResultEntry>
    ): AbilityResultEntry {
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

    private suspend fun convertOptionDtoToAbilityResultEntry(
        dto: OptionDto
    ): AbilityResultEntry {
        val option = withContext(Dispatchers.IO) {
            abilityOptionRepository.findById(dto.optionId)
        }.orElseThrow()

        return AbilityResultEntry(
            option = option,
            level = dto.level,
            numerics = dto.numeric,
        )
    }

    @Transactional
    suspend fun getOption(
        mainLevel: OptionLevel = OptionLevel.LEGENDARY,
        mode: AbilityMode = AbilityMode.NORMAL,
        locks: List<OptionDto> = listOf()
    ): AbilityResult {
        if (locks.count() > 2) {
            throw Exception("Lock count must be less than 2")
        }

        val entries = mutableListOf<AbilityResultEntry>()
        entries.addAll(locks.map { this.convertOptionDtoToAbilityResultEntry(it) })

        if (locks.find { it.level == mainLevel } == null) {
            entries.add(this.getMainOption(mainLevel, mode, entries))
        }

        val subLockCount = locks.count { it.level != mainLevel }
        when (subLockCount) {
            0 -> {
                entries.add(this.getSubOption(mainLevel, mode, entries))
                entries.add(this.getSubOption(mainLevel, mode, entries))
            }

            1 -> entries.add(this.getSubOption(mainLevel, mode, entries))
        }

        entries.sortByDescending { it.level.ordinal }

        val result = AbilityResult(entries)
        entries.forEach { it.result = result }
        withContext(Dispatchers.IO) {
            abilityResultRepository.save(result)
            abilityResultEntryRepository.saveAll(entries)
        }

        return result
    }

//    private fun simulate(
//        option: SimulationOption,
//        targets: List<AbilityResultDto>,
//        locks: List<AbilityResultDto>
//    ): List<SimulationResult> {
//        val simulationResults = mutableListOf<SimulationResult>()
//        for (index in 0 until option.maxCount) {
//            run loop@{
//                for (c in 0 until 100000) {
//                    val results = this.getOption(option.mainLevel, option.mode, locks)
//                    if (results.any { targets.contains(it) }) {
//                        simulationResults.add(SimulationResult(c, results))
//                        return@loop
//                    }
//                }
//                simulationResults.add(SimulationResult(option.maxCount, listOf()))
//            }
//        }
//
//        return simulationResults.toList()
//    }
//
//    private fun convertTargetDtoToAbilityResult(
//        targetDto: TargetDto
//    ): AbilityResultDto {
//        val option = this.abilityOptionRepository.findById(targetDto.id).orElseThrow()
//
//        return AbilityResultDto(
//            id = option.id,
//            name = option.name,
//            level = targetDto.level,
//            numeric = targetDto.numeric,
//        )
//    }
//
//    @Transactional
//    fun simulation(
//        option: SimulationOption,
//        targetDtoList: List<TargetDto>,
//        lockDtoList: List<TargetDto> = listOf()
//    ): List<SimulationResult> {
//        if (lockDtoList.count() > 2) {
//            throw Exception("Lock count must be less than 2")
//        }
//
//        val targets = targetDtoList.map { this.convertTargetDtoToAbilityResult(it) }
//        val locks = lockDtoList.map { this.convertTargetDtoToAbilityResult(it) }
//
//        return simulate(option, targets, locks)
//    }
}
