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
    private suspend fun simulate(
        dataMap: OptionDataMap,
        option: SimulationOption,
        targets: List<AbilityResultEntry>,
        locks: List<AbilityOption>
    ): List<SimulationResult> {
        val simulationResults = mutableListOf<SimulationResult>()
        for (index in 0 until option.maxCount) {
            run loop@{
                for (c in 0 until 100000) {
                    val result = this.mapleAbilityService.getOption(dataMap, option.mainLevel, option.mode, locks)
                    val entries = result.entries
                    if (entries.any { targets.contains(it) }) {
                        simulationResults.add(SimulationResult(c, entries))
                        return@loop
                    }
                }
                simulationResults.add(SimulationResult(option.maxCount, listOf()))
            }
        }

        return simulationResults.toList()
    }

    suspend fun simulation(
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
            val chunkCount = 500
            val chunks = (0 until option.maxCount step chunkCount).map { index ->
                val currentChunkSize = if (index + chunkCount > option.maxCount) option.maxCount - index else chunkCount
                async { simulate(dataMap, option.copy(maxCount = currentChunkSize), targetEntries, locks) }
            }

            awaitAll(*chunks.toTypedArray()).flatten()
        }
    }
}
