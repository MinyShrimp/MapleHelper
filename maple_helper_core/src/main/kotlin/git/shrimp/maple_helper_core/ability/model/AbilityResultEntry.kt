package git.shrimp.maple_helper_core.ability.model

import git.shrimp.maple_helper_core.global.converter.IntListToStringConverter
import git.shrimp.maple_helper_core.global.model.OptionLevel
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "ability_result_entry")
class AbilityResultEntry(
    option: AbilityOption,
    level: OptionLevel,
    numerics: List<Int>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID()
        protected set

    @Column(name = "level", nullable = false)
    var level: OptionLevel = level
        protected set

    @Convert(converter = IntListToStringConverter::class)
    @Column(name = "numerics", nullable = false)
    var numerics: List<Int> = numerics
        protected set

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    var option: AbilityOption = option
        protected set

    @Column(name = "option_id", nullable = false, insertable = false, updatable = false)
    var optionId: Int = option.id
        protected set

    @ManyToOne
    @JoinColumn(name = "result_id", nullable = false)
    var result: AbilityResult? = null

    @Column(name = "result_id", nullable = false, insertable = false, updatable = false)
    var resultId: Int? = null
}
