package git.shrimp.maple_helper.core.ability.model

import git.shrimp.maple_helper.core.global.converter.IntListToStringConverter
import git.shrimp.maple_helper.core.global.model.OptionLevel
import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "ability_numeric",
    indexes = [
        Index(name = "ability_numeric_option_id_index", columnList = "option_id"),
        Index(name = "ability_numeric_level_index", columnList = "level")
    ]
)
class AbilityNumeric(
    level: OptionLevel,
    weight: Int,
    option: AbilityOption,
    numerics: List<Int>,
) : AbilityWeight(UUID.randomUUID(), level, weight, option) {
    @Convert(converter = IntListToStringConverter::class)
    @Column(name = "numerics", nullable = false)
    var numerics: List<Int> = numerics
        protected set

    override fun toString(): String {
        return "Option(id=$id, name='${option.name}', level=$level, numerics=$numerics, weight=$weight)"
    }
}
