package git.shrimp.maple_helper_api.ability.entity.single

import git.shrimp.maple_helper_api.ability.entity.data.AbilityOptionEntity
import git.shrimp.maple_helper_api.base.converter.IntListToStringConverter
import git.shrimp.maple_helper_core.global.types.OptionLevel
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "ability_result_lock")
class AbilityResultLockEntity(
    option: AbilityOptionEntity,
    level: OptionLevel,
    numerics: List<Int>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID
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
    @JoinColumn(name = "result_id", nullable = false)
    lateinit var result: AbilityResultEntity
}
