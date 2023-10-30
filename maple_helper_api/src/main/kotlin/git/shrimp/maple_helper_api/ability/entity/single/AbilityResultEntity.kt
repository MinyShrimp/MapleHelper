package git.shrimp.maple_helper_api.ability.entity.single

import git.shrimp.maple_helper_core.ability.types.AbilityMode
import git.shrimp.maple_helper_core.global.types.OptionLevel
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "ability_result")
@EntityListeners(AuditingEntityListener::class)
class AbilityResultEntity(
    mode: AbilityMode,
    mainLevel: OptionLevel,
    entries: Set<AbilityResultEntryEntity>,
    locks: Set<AbilityResultLockEntity>
) {
    init {
        entries.forEach { it.result = this }
        locks.forEach { it.result = this }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
        protected set

    @Column(name = "mode")
    var mode: AbilityMode = mode
        protected set

    @Column(name = "main_level")
    var mainLevel: OptionLevel = mainLevel
        protected set

    @Column(name = "lock_count")
    var lockCount: Int = locks.size
        protected set

    @OneToMany(mappedBy = "result")
    var entries: MutableSet<AbilityResultEntryEntity> = entries.toMutableSet()
        protected set

    @OneToMany(mappedBy = "result")
    var locks: MutableSet<AbilityResultLockEntity> = locks.toMutableSet()
        protected set

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}
