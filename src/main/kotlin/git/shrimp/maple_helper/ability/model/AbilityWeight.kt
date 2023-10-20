package git.shrimp.maple_helper.ability.model

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
    ): this(UUID.randomUUID(), level, weight, option) {}

    enum class OptionLevel {
        RARE, EPIC, UNIQUE, LEGENDARY
    }

    override fun toString(): String {
        return "Option(id=$id, name='${option.name}', level=$level, weight=$weight)"
    }
}
