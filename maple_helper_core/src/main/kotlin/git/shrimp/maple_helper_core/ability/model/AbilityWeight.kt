package git.shrimp.maple_helper_core.ability.model

import git.shrimp.maple_helper_core.global.model.OptionLevel
import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "ability_weight",
    indexes = [
        Index(name = "ability_numeric_option_id_index", columnList = "option_id"),
        Index(name = "ability_numeric_level_index", columnList = "level")
    ]
)
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

    @Column(name = "option_id", nullable = false, insertable = false, updatable = false)
    var optionId: Int = option.id
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
