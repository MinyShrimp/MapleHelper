package git.shrimp.maple_helper.core.ability.model

import git.shrimp.maple_helper.core.global.model.OptionLevel
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "ability_weight")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class AbilityWeight(
    id: UUID,
    level: OptionLevel,
    weight: Int,
    option: AbilityOption,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = id
        protected set

    @Column(name = "level", nullable = false)
    var level: OptionLevel = level
        protected set

    @Column(name = "weight", nullable = false)
    var weight: Int = weight
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    var option: AbilityOption = option
        protected set

    constructor(
        level: OptionLevel,
        weight: Int,
        option: AbilityOption,
    ) : this(UUID.randomUUID(), level, weight, option)

    override fun toString(): String {
        return "Option(id=$id, name='${option.name}', level=$level, weight=$weight)"
    }
}
