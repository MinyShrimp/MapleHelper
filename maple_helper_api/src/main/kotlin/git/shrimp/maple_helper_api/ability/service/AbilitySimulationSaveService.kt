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
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilitySimulationSaveService(
    abilityOptionCachingService: AbilityOptionCachingService,
    private val abilitySimulationRepository: AbilitySimulationRepository,
    private val abilitySimulationEntryRepository: AbilitySimulationEntryRepository,
    private val abilitySimulationTargetRepository: AbilitySimulationTargetRepository,
    private val abilitySimulationLockRepository: AbilitySimulationLockRepository,
) {
    private val dataMap = abilityOptionCachingService.getCachedOptionData()

    private fun convert(
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
        }.toSet()
        val targets = request.targets.map { target ->
            val option = dataMap[target.level]!![target.optionId] ?: throw Exception("Invalid option id")
            AbilitySimulationTargetEntity(
                option = AbilityOptionEntity(option.id, option.name),
                level = target.level,
                numerics = target.numeric
            )
        }.toSet()
        val locks = request.locks.map { lock ->
            val option = dataMap[lock.level]!![lock.optionId] ?: throw Exception("Invalid option id")
            AbilitySimulationLockEntity(
                option = AbilityOptionEntity(option.id, option.name),
                level = lock.level,
                numerics = lock.numeric
            )
        }.toSet()

        return AbilitySimulationEntity(
            count = result.count,
            mode = request.mode,
            mainLevel = request.mainLevel,
            entries = entries,
            targets = targets,
            locks = locks
        )
    }

    @Transactional
    fun saveResultBulk(
        results: List<SimulationResult>,
        request: SimulationRequest
    ): List<AbilitySimulationEntity> {
        val entities = results.map { result -> this.convert(result, request) }

        val savedEntities = this.abilitySimulationRepository.saveAll(entities)
        entities.forEach { entity ->
            this.abilitySimulationEntryRepository.saveAll(entity.entries)
            this.abilitySimulationTargetRepository.saveAll(entity.targets)
            this.abilitySimulationLockRepository.saveAll(entity.locks)
        }

        return savedEntities
    }
}
