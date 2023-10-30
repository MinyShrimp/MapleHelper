package git.shrimp.maple_helper_api.ability.entity.simulation

import git.shrimp.maple_helper_api.ability.entity.data.AbilityOptionEntity
import git.shrimp.maple_helper_api.base.converter.IntListToStringConverter
import git.shrimp.maple_helper_core.global.types.OptionLevel
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "ability_simulation_entry")
class AbilitySimulationEntryEntity(
    option: AbilityOptionEntity,
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    var option: AbilityOptionEntity = option
        protected set

    @Column(name = "option_id", nullable = false, insertable = false, updatable = false)
    var optionId: Int = option.id
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "simulation_id", nullable = false)
    var result: AbilitySimulationEntity? = null
        set(value) {
            field = value
            this.resultId = value?.id
        }

    @Column(name = "simulation_id", nullable = false, insertable = false, updatable = false)
    var resultId: Int? = null
        protected set
}
