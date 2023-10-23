package git.shrimp.maple_helper.ability.dto

import git.shrimp.maple_helper.ability.model.AbilityMode
import git.shrimp.maple_helper.global.model.OptionLevel

data class SimulationOption(
    val maxCount: Int,
    val mainLevel: OptionLevel,
    val mode: AbilityMode
) { }
