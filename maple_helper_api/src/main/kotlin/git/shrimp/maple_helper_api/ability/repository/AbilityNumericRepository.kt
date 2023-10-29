package git.shrimp.maple_helper_api.ability.repository

import git.shrimp.maple_helper_api.ability.entity.AbilityNumeric
import git.shrimp.maple_helper_core.global.model.OptionLevel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilityNumericRepository : JpaRepository<AbilityNumeric, UUID> {
    fun findAllByOptionIdAndLevel(
        optionId: Int,
        level: OptionLevel
    ): List<AbilityNumeric>
}
