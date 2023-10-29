package git.shrimp.maple_helper_api.ability.repository

import git.shrimp.maple_helper_api.ability.entity.AbilityOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbilityOptionRepository : JpaRepository<AbilityOption, Int> {
}
