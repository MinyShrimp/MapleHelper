package git.shrimp.maple_helper.core.ability.repository

import git.shrimp.maple_helper.core.ability.model.AbilityWeight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilityWeightRepository : JpaRepository<AbilityWeight, UUID> {
}
