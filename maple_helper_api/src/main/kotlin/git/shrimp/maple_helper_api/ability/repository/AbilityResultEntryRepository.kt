package git.shrimp.maple_helper_api.ability.repository

import git.shrimp.maple_helper_api.ability.entity.AbilityResultEntryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AbilityResultEntryRepository : JpaRepository<AbilityResultEntryEntity, UUID> {
}
