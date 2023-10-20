package git.shrimp.maple_helper.ability.model

import git.shrimp.maple_helper.global.model.OptionLevel
import java.util.UUID

class AbilityWeight private constructor(
    val id: UUID,
    val level: OptionLevel,
    val weight: Int,
    val option: AbilityOption,
) {
    constructor(
        level: OptionLevel,
        weight: Int,
        option: AbilityOption,
    ): this(UUID.randomUUID(), level, weight, option)

    override fun toString(): String {
        return "Option(id=$id, name='${option.name}', level=$level, weight=$weight)"
    }
}
