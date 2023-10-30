package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.ability.types.AbilityMode

class SimulationResult(
    val id: Int,
    val count: Int,
    val mode: AbilityMode,
    val entries: List<AbilityResultEntry>,
) {
    constructor(
        count: Int,
        mode: AbilityMode,
        entries: List<AbilityResultEntry>,
    ) : this(id = 0, count = count, mode = mode, entries = entries)

    fun copy(
        id: Int = this.id,
        count: Int = 0,
        mode: AbilityMode = this.mode,
        entries: List<AbilityResultEntry> = this.entries,
    ): SimulationResult {
        return SimulationResult(
            id = id,
            count = count,
            mode = mode,
            entries = entries,
        )
    }
}
