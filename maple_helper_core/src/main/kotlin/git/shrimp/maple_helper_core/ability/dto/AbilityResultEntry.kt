package git.shrimp.maple_helper_core.ability.dto

import git.shrimp.maple_helper_core.global.types.OptionLevel

class AbilityResultEntry(
    optionId: Int,
    val name: String,
    level: OptionLevel,
    numeric: List<Int>,
) : AbilityOption(optionId, level, numeric) {
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

    override fun equals(other: Any?): Boolean {
        if (other !is AbilityResultEntry) return false
        return this.optionId == other.optionId
                && this.level == other.level
                && this.numeric.toSet() == other.numeric.toSet()
    }

    override fun hashCode(): Int {
        var result = optionId.hashCode()
        result = 31 * result + level.hashCode()
        numeric.forEach {
            result = 31 * result + it.hashCode()
        }

        return result
    }
}
