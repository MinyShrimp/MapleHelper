package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.ability.model.AbilityOption
import git.shrimp.maple_helper_core.ability.model.AbilityResultEntry
import git.shrimp.maple_helper_core.global.model.OptionLevel

class AbilityResultEntryDto(
    optionId: Int,
    name: String,
    val level: OptionLevel,
    val numeric: List<Int>,
) : AbilityOption(optionId, name) {
    companion object {
        private val regex = Regex("\\{\\d+\\}")

        fun of(
            entity: AbilityResultEntry
        ): AbilityResultEntryDto {
            return AbilityResultEntryDto(
                optionId = entity.optionId,
                name = entity.option.name,
                level = entity.level,
                numeric = entity.numerics
            )
        }
    }

    val text: String
        get() {
            var text = this.name
            regex.findAll(text)
                .zip(this.numeric.asSequence())
                .forEach { (match, n) -> text = text.replaceFirst(match.value, n.toString()) }
            return text
        }

    override fun toString(): String {
        return "[%9s] %s".format(this.level, this.text)
    }
}
