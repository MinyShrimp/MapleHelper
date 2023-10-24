package git.shrimp.maple_helper.core.ability.repository

import git.shrimp.maple_helper.core.ability.model.AbilityNumeric
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilityNumericRepository : JpaRepository<AbilityNumeric, UUID> {
}
