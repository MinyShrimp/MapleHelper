package git.shrimp.maple_helper_core.ability.repository

import git.shrimp.maple_helper_core.ability.model.AbilityWeight
import git.shrimp.maple_helper_core.global.model.OptionLevel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilityWeightRepository : JpaRepository<AbilityWeight, UUID> {
    fun findAllByLevel(
        level: OptionLevel
    ): List<AbilityWeight>
}
