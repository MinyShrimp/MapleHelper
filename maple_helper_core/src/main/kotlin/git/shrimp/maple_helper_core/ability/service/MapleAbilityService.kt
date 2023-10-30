package git.shrimp.maple_helper_core.ability.service

import git.shrimp.maple_helper_core.ability.dto.AbilityOption
import git.shrimp.maple_helper_core.ability.dto.AbilityOptionData
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import git.shrimp.maple_helper_core.ability.dto.AbilityResultEntry
import git.shrimp.maple_helper_core.ability.types.AbilityMode
import git.shrimp.maple_helper_core.ability.types.OptionDataMap
import git.shrimp.maple_helper_core.global.types.OptionLevel
import org.springframework.stereotype.Service

@Service
class MapleAbilityService {
    private fun getRandom(
        maxNumber: Int
    ): Int {
        return (0 until maxNumber).random()
    }

    private fun getRandomWeight(
        weights: List<Int>
    ): Int {
        val totalWeight = weights.sumOf { it }
        val random = this.getRandom(totalWeight)

        var sum = 0
        return weights.indexOfFirst {
            sum += it
            sum >= random
        }
    }

    private fun getOptionId(
        datas: List<AbilityOptionData>
    ): Int {
        val weights = datas.map { it.weight }
        return this.getRandomWeight(weights).let { datas[it].id }
    }

    private fun getNumeric(
        data: AbilityOptionData,
        mode: AbilityMode
    ): List<Int> {
        return when (mode) {
            AbilityMode.NORMAL -> {
                val indexed = this.getRandomWeight(data.numericWeights)
                data.numerics[indexed]
            }

            AbilityMode.MIRACLE -> data.numerics.last()
        }
    }

    private suspend fun getResult(
        dataMap: OptionDataMap,
        level: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityResultEntry> = listOf()
    ): AbilityResultEntry {
        val withoutOptionIds = withoutOptions.map { it.optionId }

        val weights = dataMap[level]!!.values.filterNot { withoutOptionIds.contains(it.id) }
        val optionId = this.getOptionId(weights.shuffled())

        val data = dataMap[level]!![optionId]!!
        val numeric = this.getNumeric(data, mode)

        return AbilityResultEntry(
            optionId = optionId,
            name = data.name,
            level = level,
            numeric = numeric
        )
    }

    private suspend fun getMainOption(
        dataMap: OptionDataMap,
        mainLevel: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityResultEntry>
    ): AbilityResultEntry {
        return this.getResult(dataMap, mainLevel, mode, withoutOptions)
    }

    private suspend fun getSubOption(
        dataMap: OptionDataMap,
        mainLevel: OptionLevel,
        mode: AbilityMode,
        withoutOptions: List<AbilityResultEntry>
    ): AbilityResultEntry {
        val random = this.getRandom(100)

        return when (mainLevel) {
            OptionLevel.LEGENDARY -> if (random < 15) {
                this.getResult(dataMap, OptionLevel.UNIQUE, mode, withoutOptions)
            } else {
                this.getResult(dataMap, OptionLevel.EPIC, mode, withoutOptions)
            }

            OptionLevel.UNIQUE -> if (random < 30) {
                this.getResult(dataMap, OptionLevel.EPIC, mode, withoutOptions)
            } else {
                this.getResult(dataMap, OptionLevel.RARE, mode, withoutOptions)
            }

            else -> this.getResult(dataMap, OptionLevel.RARE, mode, withoutOptions)
        }
    }

    suspend fun getOption(
        dataMap: OptionDataMap,
        mainLevel: OptionLevel = OptionLevel.LEGENDARY,
        mode: AbilityMode = AbilityMode.NORMAL,
        locks: List<AbilityOption> = listOf()
    ): AbilityResult {
        if (locks.count() > 2) {
            throw Exception("Lock count must be less than 2")
        }

        val entries = mutableListOf<AbilityResultEntry>()
        val locksEntries = locks.map { it.to(dataMap) }
        entries.addAll(locksEntries)

        if (locks.find { it.level == mainLevel } == null) {
            entries.add(this.getMainOption(dataMap, mainLevel, mode, entries))
        }

        val subLockCount = locks.count { it.level != mainLevel }
        when (subLockCount) {
            0 -> {
                entries.add(this.getSubOption(dataMap, mainLevel, mode, entries))
                entries.add(this.getSubOption(dataMap, mainLevel, mode, entries))
            }

            1 -> entries.add(this.getSubOption(dataMap, mainLevel, mode, entries))
        }

        entries.sortByDescending { it.level.ordinal }
        return AbilityResult(
            mode = mode,
            entries = entries,
        )
    }
}
