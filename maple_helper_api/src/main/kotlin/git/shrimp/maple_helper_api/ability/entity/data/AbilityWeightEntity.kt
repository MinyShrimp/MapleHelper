package git.shrimp.maple_helper_api.ability.entity.data

import git.shrimp.maple_helper_core.global.types.OptionLevel
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
class AbilityWeightEntity(
    level: OptionLevel,
    weight: Int,
    option: AbilityOptionEntity,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID()
        protected set

    @Column(name = "level", nullable = false)
    var level: OptionLevel = level
        protected set

    @Column(name = "weight", nullable = false)
    var weight: Int = weight
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    var option: AbilityOptionEntity = option
        protected set

    @Column(name = "option_id", nullable = false, insertable = false, updatable = false)
    var optionId: Int = option.id
        protected set
}
