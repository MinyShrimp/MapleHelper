package git.shrimp.maple_helper_api.ability.entity

import jakarta.persistence.*

@Entity
@Table(name = "ability_option")
class AbilityOptionEntity(
    id: Int,
    name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = id
        protected set

    @Column(name = "name", nullable = false, unique = true)
    var name: String = name
        protected set

    @OneToMany(mappedBy = "option", fetch = FetchType.LAZY)
    var weights: MutableSet<AbilityWeightEntity> = mutableSetOf()
        protected set

    @OneToMany(mappedBy = "option", fetch = FetchType.LAZY)
    var numerics: MutableSet<AbilityNumericEntity> = mutableSetOf()
        protected set
}
