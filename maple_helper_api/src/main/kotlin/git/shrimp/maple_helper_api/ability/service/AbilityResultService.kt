package git.shrimp.maple_helper_api.ability.service

import git.shrimp.maple_helper_api.ability.dto.OptionRequest
import git.shrimp.maple_helper_api.ability.entity.AbilityOptionEntity
import git.shrimp.maple_helper_api.ability.entity.AbilityResultEntity
import git.shrimp.maple_helper_api.ability.entity.AbilityResultEntryEntity
import git.shrimp.maple_helper_api.ability.entity.AbilityResultLockEntity
import git.shrimp.maple_helper_api.ability.repository.AbilityResultEntryRepository
import git.shrimp.maple_helper_api.ability.repository.AbilityResultLockRepository
import git.shrimp.maple_helper_api.ability.repository.AbilityResultRepository
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import git.shrimp.maple_helper_core.ability.types.OptionDataMap
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilityResultService(
    private val abilityResultRepository: AbilityResultRepository,
    private val abilityResultEntryRepository: AbilityResultEntryRepository,
    private val abilityResultLockRepository: AbilityResultLockRepository,
    private val abilityOptionCachingService: AbilityOptionCachingService,
) {
    private fun convert(
        dataMap: OptionDataMap,
        result: AbilityResult,
        request: OptionRequest
    ): AbilityResultEntity {
        val entries = result.entries.map { entry ->
            val option = dataMap[entry.level]!![entry.optionId] ?: throw Exception("Invalid option id")
            AbilityResultEntryEntity(
                option = AbilityOptionEntity(option.id, option.name),
                level = entry.level,
                numerics = entry.numeric
            )
        }
        val locks = request.locks.map { lock ->
            val option = dataMap[lock.level]!![lock.optionId] ?: throw Exception("Invalid option id")
            AbilityResultLockEntity(
                option = AbilityOptionEntity(option.id, option.name),
                level = lock.level,
                numerics = lock.numeric
            )
        }

        return AbilityResultEntity(
            mode = result.mode,
            mainLevel = request.mainLevel,
            entries = entries,
            locks = locks
        )
    }

    @Transactional
    fun saveResult(
        result: AbilityResult,
        request: OptionRequest
    ): AbilityResultEntity {
        val dataMap = this.abilityOptionCachingService.getCachedOptionData()
        val entity = this.convert(dataMap, result, request)

        val savedEntity = this.abilityResultRepository.save(entity)
        this.abilityResultEntryRepository.saveAll(entity.entries)
        this.abilityResultLockRepository.saveAll(entity.locks)

        return savedEntity
    }

    @Transactional
    fun saveResultBulk(
        results: List<AbilityResult>,
        request: OptionRequest
    ): List<AbilityResultEntity> {
        val dataMap = this.abilityOptionCachingService.getCachedOptionData()
        val entities = results.map { convert(dataMap, it, request) }

        val savedEntities = this.abilityResultRepository.saveAll(entities)
        this.abilityResultEntryRepository.saveAll(savedEntities.flatMap { it.entries })
        this.abilityResultLockRepository.saveAll(savedEntities.flatMap { it.locks })

        return savedEntities
    }
}
