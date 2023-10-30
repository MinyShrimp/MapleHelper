package git.shrimp.maple_helper_api.ability.entity.simulation

import git.shrimp.maple_helper_core.ability.types.AbilityMode
import git.shrimp.maple_helper_core.global.types.OptionLevel
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "ability_simulation")
@EntityListeners(AuditingEntityListener::class)
class AbilitySimulationEntity(
    count: Int,
    mode: AbilityMode,
    mainLevel: OptionLevel,
    entries: Set<AbilitySimulationEntryEntity>,
    targets: Set<AbilitySimulationTargetEntity>,
    locks: Set<AbilitySimulationLockEntity>
) {
    init {
        entries.forEach { it.result = this }
        targets.forEach { it.result = this }
        locks.forEach { it.result = this }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
        protected set

    @Column(name = "count")
    var count: Int = count
        protected set

    @Column(name = "mode")
    var mode: AbilityMode = mode
        protected set

    @Column(name = "main_level")
    var mainLevel: OptionLevel = mainLevel
        protected set

    @OneToMany(mappedBy = "result")
    var entries: MutableSet<AbilitySimulationEntryEntity> = entries.toMutableSet()
        protected set

    @Column(name = "target_count")
    var targetCount: Int = targets.size
        protected set

    @OneToMany(mappedBy = "result")
    var targets: MutableSet<AbilitySimulationTargetEntity> = targets.toMutableSet()
        protected set

    @Column(name = "lock_count")
    var lockCount: Int = locks.size
        protected set

    @OneToMany(mappedBy = "result")
    var locks: MutableSet<AbilitySimulationLockEntity> = locks.toMutableSet()
        protected set

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}
