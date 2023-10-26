package git.shrimp.maple_helper_core.ability.repository

import git.shrimp.maple_helper_core.ability.model.AbilityResultEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AbilityResultEntryRepository : JpaRepository<AbilityResultEntry, UUID> {}
