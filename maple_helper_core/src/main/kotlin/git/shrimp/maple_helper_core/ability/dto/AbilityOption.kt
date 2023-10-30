package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.ability.types.OptionDataMap
import git.shrimp.maple_helper_core.global.types.OptionLevel

open class AbilityOption(
    val optionId: Int,
    val level: OptionLevel,
    val numeric: List<Int>,
) {
    fun to(
        dataMap: OptionDataMap
    ): AbilityResultEntry {
        return AbilityResultEntry(
            optionId = this.optionId,
            name = dataMap[this.level]!![this.optionId]!!.name,
            level = this.level,
            numeric = this.numeric
        )
    }
}
