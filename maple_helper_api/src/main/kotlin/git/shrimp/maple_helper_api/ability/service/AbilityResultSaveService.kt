package git.shrimp.maple_helper_api.ability.service

import git.shrimp.maple_helper_api.ability.dto.OptionRequest
import git.shrimp.maple_helper_api.ability.entity.data.AbilityOptionEntity
import git.shrimp.maple_helper_api.ability.entity.single.AbilityResultEntity
import git.shrimp.maple_helper_api.ability.entity.single.AbilityResultEntryEntity
import git.shrimp.maple_helper_api.ability.entity.single.AbilityResultLockEntity
import git.shrimp.maple_helper_api.ability.repository.single.AbilityResultEntryRepository
import git.shrimp.maple_helper_api.ability.repository.single.AbilityResultLockRepository
import git.shrimp.maple_helper_api.ability.repository.single.AbilityResultRepository
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilityResultSaveService(
    abilityOptionCachingService: AbilityOptionCachingService,
    private val abilityResultRepository: AbilityResultRepository,
    private val abilityResultEntryRepository: AbilityResultEntryRepository,
    private val abilityResultLockRepository: AbilityResultLockRepository,
) {
    private val dataMap = abilityOptionCachingService.getCachedOptionData()

    private fun convert(
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
        val entity = this.convert(result, request)

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
        val entities = results.map { this.convert(it, request) }

        val savedEntities = this.abilityResultRepository.saveAll(entities)
        this.abilityResultEntryRepository.saveAll(savedEntities.flatMap { it.entries })
        this.abilityResultLockRepository.saveAll(savedEntities.flatMap { it.locks })

        return savedEntities
    }
}