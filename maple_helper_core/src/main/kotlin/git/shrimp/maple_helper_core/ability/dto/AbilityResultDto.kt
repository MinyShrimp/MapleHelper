package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.ability.model.AbilityResult

class AbilityResultDto(
    val id: Int,
    val entries: List<AbilityResultEntryDto>,
) {
    companion object {
        fun of(
            entity: AbilityResult
        ): AbilityResultDto {
            return AbilityResultDto(
                id = entity.id,
                entries = entity.entries.map { AbilityResultEntryDto.of(it) }
            )
        }
    }
}
