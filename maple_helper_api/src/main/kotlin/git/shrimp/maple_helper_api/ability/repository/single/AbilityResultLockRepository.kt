package git.shrimp.maple_helper_api.ability.repository.single

import git.shrimp.maple_helper_api.ability.entity.single.AbilityResultLockEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilityResultLockRepository : JpaRepository<AbilityResultLockEntity, UUID> {
}
