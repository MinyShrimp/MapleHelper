package git.shrimp.maple_helper_api.ability.repository.data

import git.shrimp.maple_helper_api.ability.entity.data.AbilityWeightEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AbilityWeightRepository : JpaRepository<AbilityWeightEntity, UUID> {
}
