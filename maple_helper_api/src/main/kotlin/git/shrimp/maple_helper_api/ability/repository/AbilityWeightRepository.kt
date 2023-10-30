package git.shrimp.maple_helper_api.ability.repository

import git.shrimp.maple_helper_api.ability.entity.AbilityWeightEntity
import git.shrimp.maple_helper_core.global.types.OptionLevel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AbilityWeightRepository : JpaRepository<AbilityWeightEntity, UUID> {
    fun findOneByOptionIdAndLevel(
        optionId: Int,
        level: OptionLevel
    ): AbilityWeightEntity?
}
