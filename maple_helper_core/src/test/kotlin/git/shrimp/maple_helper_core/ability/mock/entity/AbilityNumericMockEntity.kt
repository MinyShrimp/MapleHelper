package git.shrimp.maple_helper_core.ability.mock.entity

import git.shrimp.maple_helper_core.global.types.OptionLevel
import java.util.*

class AbilityNumericMockEntity(
    val level: OptionLevel,
    val weight: Int,
    val numeric: List<Int>,
    val option: AbilityOptionMockEntity
) {
    val id: UUID = UUID.randomUUID()
    val optionId: Int = option.id
}
