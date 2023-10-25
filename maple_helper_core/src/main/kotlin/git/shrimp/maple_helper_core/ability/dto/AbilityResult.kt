package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.ability.model.AbilityOption
import git.shrimp.maple_helper_core.global.model.OptionLevel

class AbilityResult(
    id: Int,
    name: String,
    val level: OptionLevel,
    val numeric: List<Int>,
) : AbilityOption(id, name) {
    companion object {
        private val regex = Regex("\\{\\d+\\}")
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

    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + this.id
        hash = hash * 31 + this.level.ordinal
        for (n in this.numeric) {
            hash = hash * 31 + n
        }

        return hash
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is AbilityResult -> {
                this.id == other.id
                        && this.level == other.level
                        && this.numeric.toSet() == other.numeric.toSet()
            }

            else -> false
        }
    }
}
