package git.shrimp.maple_helper.core.ability.model

import git.shrimp.maple_helper.core.global.model.OptionLevel
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "ability_numeric")
class AbilityNumeric(
    level: OptionLevel,
    weight: Int,
    option: AbilityOption,
    numerics: List<Int>,
) : AbilityWeight(UUID.randomUUID(), level, weight, option) {
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.UUID)
    override var id: UUID = super.id
        protected set

    @get:Column(name = "level", nullable = false)
    override var level: OptionLevel = super.level
        protected set

    @get:Column(name = "weight", nullable = false)
    override var weight: Int = super.weight
        protected set

    @get:ManyToOne(fetch = FetchType.LAZY, optional = false)
    @get:JoinColumn(name = "option_id", nullable = false)
    override var option: AbilityOption = super.option
        protected set

    @ElementCollection
    var numerics: List<Int> = numerics
        protected set

    override fun toString(): String {
        return "Option(id=$id, name='${option.name}', level=$level, numerics=$numerics, weight=$weight)"
    }
}
