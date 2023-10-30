package git.shrimp.maple_helper_api.ability.service

import git.shrimp.maple_helper_api.ability.dto.SimulationRequest
import git.shrimp.maple_helper_api.ability.entity.data.AbilityOptionEntity
import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationEntity
import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationEntryEntity
import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationLockEntity
import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationTargetEntity
import git.shrimp.maple_helper_api.ability.repository.simulation.AbilitySimulationEntryRepository
import git.shrimp.maple_helper_api.ability.repository.simulation.AbilitySimulationLockRepository
import git.shrimp.maple_helper_api.ability.repository.simulation.AbilitySimulationRepository
import git.shrimp.maple_helper_api.ability.repository.simulation.AbilitySimulationTargetRepository
import git.shrimp.maple_helper_core.ability.dto.SimulationResult
import git.shrimp.maple_helper_core.ability.types.OptionDataMap
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilitySimulationService(
    private val abilitySimulationRepository: AbilitySimulationRepository,
    private val abilitySimulationEntryRepository: AbilitySimulationEntryRepository,
    private val abilitySimulationTargetRepository: AbilitySimulationTargetRepository,
    private val abilitySimulationLockRepository: AbilitySimulationLockRepository,
    private val abilityOptionCachingService: AbilityOptionCachingService,
) {
    private fun convert(
        dataMap: OptionDataMap,
        result: SimulationResult,
        request: SimulationRequest
    ): AbilitySimulationEntity {
        val entries = result.entries.map { entry ->
            val option = dataMap[entry.level]!![entry.optionId] ?: throw Exception("Invalid option id")
            AbilitySimulationEntryEntity(
                option = AbilityOptionEntity(option.id, option.name),
                level = entry.level,
                numerics = entry.numeric
            )
        }
        val targets = request.targets.map { target ->
            val option = dataMap[target.level]!![target.optionId] ?: throw Exception("Invalid option id")
            AbilitySimulationTargetEntity(
                option = AbilityOptionEntity(option.id, option.name),
                level = target.level,
                numerics = target.numeric
            )
        }
        val locks = request.locks.map { lock ->
            val option = dataMap[lock.level]!![lock.optionId] ?: throw Exception("Invalid option id")
            AbilitySimulationLockEntity(
                option = AbilityOptionEntity(option.id, option.name),
                level = lock.level,
                numerics = lock.numeric
            )
        }

        return AbilitySimulationEntity(
            count = result.count,
            mode = request.mode,
            mainLevel = request.mainLevel,
            entries = entries.toSet(),
            targets = targets.toSet(),
            locks = locks.toSet()
        )
    }

    @Transactional
    fun saveResult(
        result: SimulationResult,
        request: SimulationRequest
    ): AbilitySimulationEntity {
        val dataMap = this.abilityOptionCachingService.getCachedOptionData()
        val entity = this.convert(dataMap, result, request)

        val savedEntity = this.abilitySimulationRepository.save(entity)
        this.abilitySimulationEntryRepository.saveAll(entity.entries)
        this.abilitySimulationTargetRepository.saveAll(entity.targets)
        this.abilitySimulationLockRepository.saveAll(entity.locks)

        return savedEntity
    }
}
