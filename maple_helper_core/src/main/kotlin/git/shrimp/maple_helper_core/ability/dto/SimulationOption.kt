package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.ability.types.AbilityMode
import git.shrimp.maple_helper_core.global.types.OptionLevel

data class SimulationOption(
    val count: Int,
    val mainLevel: OptionLevel,
    val mode: AbilityMode
) {}
