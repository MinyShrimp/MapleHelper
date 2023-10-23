package git.shrimp.maple_helper.ability.model

import git.shrimp.maple_helper.global.model.OptionLevel
import java.util.UUID

class AbilityNumeric(
    level: OptionLevel,
    weight: Int,
    option: AbilityOption,
    val numeric: Array<Int>,
) : AbilityWeight(UUID.randomUUID(), level, weight, option) {
    override fun toString(): String {
        return "Option(id=$id, name='${option.name}', level=$level, numeric=$numeric, weight=$weight)"
    }
}
