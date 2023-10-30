package git.shrimp.maple_helper_api.ability.repository.simulation

import git.shrimp.maple_helper_api.ability.entity.simulation.AbilitySimulationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbilitySimulationRepository : JpaRepository<AbilitySimulationEntity, Int> {
}
