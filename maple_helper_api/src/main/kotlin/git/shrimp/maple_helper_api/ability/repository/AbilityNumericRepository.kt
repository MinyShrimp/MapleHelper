package git.shrimp.maple_helper_api.ability.repository

import git.shrimp.maple_helper_api.ability.entity.AbilityNumericEntity
import git.shrimp.maple_helper_core.global.types.OptionLevel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilityNumericRepository : JpaRepository<AbilityNumericEntity, UUID> {
    fun findAllByOptionIdAndLevelOrderByWeightDescNumericsAsc(
        optionId: Int,
        level: OptionLevel
    ): List<AbilityNumericEntity>
}
