package git.shrimp.maple_helper_api.ability.repository

import git.shrimp.maple_helper_api.ability.entity.AbilityWeight
import org.springframework.data.jpa.repository.JpaRepository

interface AbilityWeightRepository : JpaRepository<AbilityWeight, Int> {
}
