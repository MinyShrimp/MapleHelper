package git.shrimp.maple_helper.ability.model

open class AbilityOption(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return "Option(id=$id, name='${name}')"
    }
}
