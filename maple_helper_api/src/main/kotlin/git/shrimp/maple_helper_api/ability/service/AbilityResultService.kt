package git.shrimp.maple_helper_api.ability.service

import git.shrimp.maple_helper_api.ability.entity.AbilityResultEntity
import git.shrimp.maple_helper_api.ability.entity.AbilityResultEntryEntity
import git.shrimp.maple_helper_api.ability.repository.AbilityOptionRepository
import git.shrimp.maple_helper_api.ability.repository.AbilityResultEntryRepository
import git.shrimp.maple_helper_api.ability.repository.AbilityResultRepository
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilityResultService(
    private val abilityOptionRepository: AbilityOptionRepository,
    private val abilityResultRepository: AbilityResultRepository,
    private val abilityResultEntryRepository: AbilityResultEntryRepository
) {
    @Transactional
    fun saveResult(
        result: AbilityResult
    ): AbilityResultEntity {
        val entries = result.entries.map {
            val option = abilityOptionRepository.findById(it.optionId).orElseThrow()
            AbilityResultEntryEntity(
                option = option,
                level = it.level,
                numerics = it.numeric
            )
        }
        val entity = AbilityResultEntity(result.mode, entries)

        val savedEntity = abilityResultRepository.save(entity)
        abilityResultEntryRepository.saveAll(entries)

        return savedEntity
    }
}
