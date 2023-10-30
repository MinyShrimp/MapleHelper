package git.shrimp.maple_helper_api.ability.repository

import git.shrimp.maple_helper_api.ability.entity.AbilityResultEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AbilityResultRepository : JpaRepository<AbilityResultEntity, Int> {
}
