package git.shrimp.maple_helper_api.ability.repository.single

import git.shrimp.maple_helper_api.ability.entity.single.AbilityResultEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AbilityResultRepository : JpaRepository<AbilityResultEntity, Int> {
    @Query(
        """
        SELECT a
        FROM AbilityResultEntity a
        JOIN FETCH a.entries e
        JOIN FETCH e.option o
        JOIN FETCH a.locks l
        JOIN FETCH l.option lo
        WHERE a.id = :id
    """
    )
    fun findOneById(
        id: Int
    ): AbilityResultEntity?
}
