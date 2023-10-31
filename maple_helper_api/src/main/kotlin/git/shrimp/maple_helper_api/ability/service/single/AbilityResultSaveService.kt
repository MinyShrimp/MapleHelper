package git.shrimp.maple_helper_api.ability.service.single

import git.shrimp.maple_helper_api.ability.dto.OptionRequest
import git.shrimp.maple_helper_api.ability.entity.data.AbilityOptionEntity
import git.shrimp.maple_helper_api.ability.entity.single.AbilityResultEntity
import git.shrimp.maple_helper_api.ability.entity.single.AbilityResultEntryEntity
import git.shrimp.maple_helper_api.ability.entity.single.AbilityResultLockEntity
import git.shrimp.maple_helper_api.ability.repository.single.AbilityResultRepository
import git.shrimp.maple_helper_api.ability.service.data.AbilityOptionCachingService
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilityResultSaveService(
    abilityOptionCachingService: AbilityOptionCachingService,
    private val abilityResultRepository: AbilityResultRepository
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

        val entity = AbilityResultEntity(
            mode = result.mode,
            mainLevel = request.mainLevel,
            entries = entries,
            locks = locks
        )

        entity.entries.forEach { it.result = entity }
        entity.locks.forEach { it.result = entity }
        return entity
    }

    @Transactional
    fun saveResult(
        result: AbilityResult,
        request: OptionRequest
    ): AbilityResultEntity {
        val entity = this.convert(result, request)
        return this.abilityResultRepository.save(entity)
    }

    @Transactional
    fun saveResultBulk(
        results: List<AbilityResult>,
        request: OptionRequest
    ): List<AbilityResultEntity> {
        val entities = results.map { this.convert(it, request) }
        return this.abilityResultRepository.saveAll(entities)
    }
}
