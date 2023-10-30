package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.ability.types.AbilityMode

class AbilityResult private constructor(
    val id: Int,
    val mode: AbilityMode,
    val entries: List<AbilityResultEntry>,
) {
    constructor(
        mode: AbilityMode,
        entries: List<AbilityResultEntry>,
    ) : this(id = 0, mode = mode, entries = entries)

    fun copy(
        id: Int = this.id,
        mode: AbilityMode = this.mode,
        entries: List<AbilityResultEntry> = this.entries,
    ): AbilityResult {
        return AbilityResult(
            id = id,
            mode = mode,
            entries = entries,
        )
    }
}
