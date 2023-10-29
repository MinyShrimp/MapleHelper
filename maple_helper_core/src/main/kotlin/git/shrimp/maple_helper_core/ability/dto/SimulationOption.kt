package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.global.model.OptionLevel

data class SimulationOption(
    val maxCount: Int,
    val mainLevel: OptionLevel,
    val mode: AbilityMode
) {}
