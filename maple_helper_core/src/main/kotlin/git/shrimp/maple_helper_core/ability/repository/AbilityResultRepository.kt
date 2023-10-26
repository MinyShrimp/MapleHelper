package git.shrimp.maple_helper_core.ability.repository

import git.shrimp.maple_helper_core.ability.model.AbilityResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbilityResultRepository : JpaRepository<AbilityResult, Int> {
}
