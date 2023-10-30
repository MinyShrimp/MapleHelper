package git.shrimp.maple_helper_api.ability.repository.data

import git.shrimp.maple_helper_api.ability.entity.data.AbilityOptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AbilityOptionRepository : JpaRepository<AbilityOptionEntity, Int> {
    @Query(
        """
        SELECT a 
        FROM AbilityOptionEntity a
        JOIN FETCH a.weights w
        JOIN FETCH a.numerics n
        ORDER BY a.id
    """
    )
    fun findAllOrderById(): List<AbilityOptionEntity>
}
