package git.shrimp.maple_helper_api.ability.repository.simulation

import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationLockEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilitySimulationLockRepository : JpaRepository<AbilitySimulationLockEntity, UUID> {
}
