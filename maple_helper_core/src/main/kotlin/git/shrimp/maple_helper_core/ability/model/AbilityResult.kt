package git.shrimp.maple_helper_core.ability.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "ability_result")
@EntityListeners(AuditingEntityListener::class)
class AbilityResult(
    resultEntryList: List<AbilityResultEntry>,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
        protected set

    @OneToMany(mappedBy = "result")
    var entries: MutableList<AbilityResultEntry> = resultEntryList.toMutableList()
        protected set

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}
