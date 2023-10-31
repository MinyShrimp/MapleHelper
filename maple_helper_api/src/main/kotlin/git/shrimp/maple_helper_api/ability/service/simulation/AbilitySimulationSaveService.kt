package git.shrimp.maple_helper_api.ability.service.simulation

import git.shrimp.maple_helper_api.ability.dto.SimulationRequest
import git.shrimp.maple_helper_api.ability.entity.data.AbilityOptionEntity
import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationEntity
import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationEntryEntity
import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationLockEntity
import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationTargetEntity
import git.shrimp.maple_helper_api.ability.repository.simulation.AbilitySimulationRepository
import git.shrimp.maple_helper_api.ability.service.data.AbilityOptionCachingService
import git.shrimp.maple_helper_core.ability.dto.SimulationResult
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilitySimulationSaveService(
    abilityOptionCachingService: AbilityOptionCachingService,
    private val abilitySimulationRepository: AbilitySimulationRepository,
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

        val entity = AbilitySimulationEntity(
            count = result.count,
            mode = request.mode,
            mainLevel = request.mainLevel,
            entries = entries,
            targets = targets,
            locks = locks
        )

        entity.entries.forEach { it.result = entity }
        entity.targets.forEach { it.result = entity }
        entity.locks.forEach { it.result = entity }
        return entity
    }

    @Transactional
    fun saveResult(
        result: SimulationResult,
        request: SimulationRequest
    ): AbilitySimulationEntity {
        val entity = this.convert(result, request)
        return this.abilitySimulationRepository.save(entity)
    }

    @Transactional
    fun saveResultBulk(
        results: List<SimulationResult>,
        request: SimulationRequest
    ): List<AbilitySimulationEntity> {
        val entities = results.map { result -> this.convert(result, request) }
        return this.abilitySimulationRepository.saveAll(entities)
    }
}
