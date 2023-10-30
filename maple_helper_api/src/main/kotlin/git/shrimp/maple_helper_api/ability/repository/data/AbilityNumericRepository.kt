package git.shrimp.maple_helper_api.ability.repository.data

import git.shrimp.maple_helper_api.ability.entity.data.AbilityNumericEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilityNumericRepository : JpaRepository<AbilityNumericEntity, UUID> {
}
