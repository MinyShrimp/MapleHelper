package git.shrimp.maple_helper_core.ability.service

import git.shrimp.maple_helper_core.ability.dto.*
import git.shrimp.maple_helper_core.ability.repository.AbilityOptionRepository
import jakarta.transaction.Transactional
import kotlinx.coroutines.*
import org.springframework.stereotype.Service

@Service
class MapleAbilitySimulationService(
    private val abilityOptionRepository: AbilityOptionRepository,
    private val mapleAbilityService: MapleAbilityService
) {
    suspend fun convertOptionDto(
        dto: AbilityOptionDto
    ): AbilityResultEntryDto {
        val option = withContext(Dispatchers.IO) {
            abilityOptionRepository.findById(dto.optionId)
        }.orElseThrow()

        return AbilityResultEntryDto(
            optionId = option.id,
            name = option.name,
            level = dto.level,
            numeric = dto.numeric,
        )
    }

    private suspend fun simulate(
        option: SimulationOption,
        targets: List<AbilityResultEntryDto>,
        locks: List<AbilityOptionDto>
    ): List<SimulationResultDto> {
        val simulationResults = mutableListOf<SimulationResultDto>()
        for (index in 0 until option.maxCount) {
            run loop@{
                for (c in 0 until 100000) {
                    val result = this.mapleAbilityService.getOption(option.mainLevel, option.mode, locks)
                    val entries = AbilityResultDto.of(result).entries
                    if (entries.any { targets.contains(it) }) {
                        simulationResults.add(SimulationResultDto(c, entries))
                        return@loop
                    }
                }
                simulationResults.add(SimulationResultDto(option.maxCount, listOf()))
            }
        }

        return simulationResults.toList()
    }

    @Transactional
    suspend fun simulation(
        option: SimulationOption,
        targets: List<AbilityOptionDto>,
        locks: List<AbilityOptionDto> = listOf()
    ): List<SimulationResultDto> {
        if (targets.count() + locks.count() > 2) {
            throw Exception("Lock and Target count must be less than 2")
        }

        val targetEntries = targets.map { this.convertOptionDto(it) }

        return coroutineScope {
            val chunkCount = 100
            val chunks = (0 until option.maxCount step chunkCount).map { index ->
                val currentChunkSize = if (index + chunkCount > option.maxCount) option.maxCount - index else chunkCount
                async { simulate(option.copy(maxCount = currentChunkSize), targetEntries, locks) }
            }

            awaitAll(*chunks.toTypedArray()).flatten()
        }
    }
}
