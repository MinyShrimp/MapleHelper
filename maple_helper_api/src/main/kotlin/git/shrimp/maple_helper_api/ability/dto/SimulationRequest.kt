package git.shrimp.maple_helper_api.ability.dto

import git.shrimp.maple_helper_core.ability.dto.AbilityOption
import git.shrimp.maple_helper_core.ability.types.AbilityMode
import git.shrimp.maple_helper_core.global.types.OptionLevel

data class SimulationRequest(
    val count: Int = 100,
    val stream: Boolean = false,
    val mainLevel: OptionLevel = OptionLevel.LEGENDARY,
    val mode: AbilityMode = AbilityMode.NORMAL,
    val targets: List<AbilityOption> = emptyList(),
    val locks: List<AbilityOption> = emptyList(),
) {
}
