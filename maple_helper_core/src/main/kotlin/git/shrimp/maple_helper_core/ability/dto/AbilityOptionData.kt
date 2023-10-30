package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.global.types.OptionLevel

data class AbilityOptionData(
    val id: Int,
    val name: String,
    val level: OptionLevel,
    val weight: Int,
    val numerics: List<List<Int>>,
    val numericWeights: List<Int> = listOf(20, 20, 20, 15, 15, 10)
) {
}
