package git.shrimp.maple_helper.core.ability.model

import jakarta.persistence.*

@Entity
@Table(name = "ability_option")
class AbilityOption(
    id: Int,
    name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = id
        protected set

    @Column(name = "name", nullable = false, unique = true)
    var name: String = name
        protected set

    override fun toString(): String {
        return "Option(id=$id, name='${name}')"
    }
}
