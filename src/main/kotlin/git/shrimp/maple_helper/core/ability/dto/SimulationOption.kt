package git.shrimp.maple_helper.core.ability.dto

import git.shrimp.maple_helper.core.ability.model.AbilityMode
import git.shrimp.maple_helper.core.global.model.OptionLevel

data class SimulationOption(
    val maxCount: Int,
    val mainLevel: OptionLevel,
    val mode: AbilityMode
) { }
