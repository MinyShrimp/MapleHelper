package git.shrimp.maple_helper.ability.model

import git.shrimp.maple_helper.global.model.OptionLevel
import java.util.UUID

class AbilityNumeric private constructor(
    val id: UUID,
    val level: OptionLevel,
    val numeric: Int,
    val weight: Int,
    val option: AbilityOption,
) {
    constructor(
        level: OptionLevel,
        numeric: Int,
        weight: Int,
        option: AbilityOption,
    ): this(UUID.randomUUID(), level, numeric, weight, option)

    override fun toString(): String {
        return "Option(id=$id, name='${option.name}', level=$level, numeric=$numeric, weight=$weight)"
    }
}
