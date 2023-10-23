package git.shrimp.maple_helper.ability.dto

import git.shrimp.maple_helper.ability.model.AbilityOption
import git.shrimp.maple_helper.global.model.OptionLevel

class AbilityResult(
    id: Int,
    name: String,
    val level: OptionLevel,
    val numeric: Array<Int>,
): AbilityOption(id, name) {
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
        return "ID: %2d, [%9s] %s".format(this.id, this.level, this.text)
    }
}
