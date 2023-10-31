package git.shrimp.maple_helper_core.ability.service

import git.shrimp.maple_helper_core.ability.dto.AbilityOption
import git.shrimp.maple_helper_core.ability.dto.AbilityResultEntry
import git.shrimp.maple_helper_core.ability.dto.SimulationOption
import git.shrimp.maple_helper_core.ability.dto.SimulationResult
import git.shrimp.maple_helper_core.ability.types.OptionDataMap
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class MapleAbilitySimulationService(
    private val mapleAbilityService: MapleAbilityService
) {
    companion object {
        const val MAX_COUNT = 100000
    }

    private suspend fun run(
        dataMap: OptionDataMap,
        option: SimulationOption,
        targets: List<AbilityResultEntry>,
        locks: List<AbilityOption>
    ): SimulationResult? {
        List(MAX_COUNT) { c ->
            val result = mapleAbilityService.getOption(dataMap, option.mainLevel, option.mode, locks)
            if (result.entries.any { targets.contains(it) }) {
                return SimulationResult(c, option.mode, result.entries)
            }
        }
        return null
    }

    suspend fun simulateOne(
        dataMap: OptionDataMap,
        option: SimulationOption,
        targets: List<AbilityOption>,
        locks: List<AbilityOption> = listOf()
    ): SimulationResult? {
        if (targets.count() + locks.count() > 2) {
            throw Exception("Lock and Target count must be less than 2")
        }

        val targetEntries = targets.map { it.to(dataMap) }
        return run(dataMap, option, targetEntries, locks)
    }

    suspend fun simulate(
        dataMap: OptionDataMap,
        option: SimulationOption,
        targets: List<AbilityOption>,
        locks: List<AbilityOption> = listOf()
    ): List<SimulationResult> {
        if (targets.count() + locks.count() > 2) {
            throw Exception("Lock and Target count must be less than 2")
        }

        val targetEntries = targets.map { it.to(dataMap) }
        return coroutineScope {
            List(option.count) {
                async { run(dataMap, option, targetEntries, locks) }
            }.awaitAll().filterNotNull()
        }
    }
}
