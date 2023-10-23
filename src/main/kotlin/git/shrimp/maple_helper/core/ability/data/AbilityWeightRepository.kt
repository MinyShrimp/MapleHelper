package git.shrimp.maple_helper.core.ability.data

import git.shrimp.maple_helper.core.ability.model.AbilityOption
import git.shrimp.maple_helper.core.ability.model.AbilityWeight
import git.shrimp.maple_helper.core.global.model.OptionLevel
import org.springframework.stereotype.Service

@Service
class AbilityWeightRepository(
    private val abilityOptionRepository: AbilityOptionRepository
) {
    private val tableByRare = mutableMapOf<OptionLevel, List<AbilityWeight>>()

    init {
        // RARE
        this.add(0, OptionLevel.RARE, 389)
        this.add(1, OptionLevel.RARE, 389)
        this.add(2, OptionLevel.RARE, 389)
        this.add(3, OptionLevel.RARE, 389)
        this.add(4, OptionLevel.RARE, 372)
        this.add(5, OptionLevel.RARE, 372)
        this.add(6, OptionLevel.RARE, 0)
        this.add(7, OptionLevel.RARE, 0)
        this.add(8, OptionLevel.RARE, 0)
        this.add(9, OptionLevel.RARE, 346)
        this.add(10, OptionLevel.RARE, 0)
        this.add(11, OptionLevel.RARE, 260)
        this.add(12, OptionLevel.RARE, 260)
        this.add(13, OptionLevel.RARE, 260)
        this.add(14, OptionLevel.RARE, 260)
        this.add(15, OptionLevel.RARE, 0)
        this.add(16, OptionLevel.RARE, 0)
        this.add(17, OptionLevel.RARE, 0)
        this.add(18, OptionLevel.RARE, 0)
        this.add(19, OptionLevel.RARE, 0)
        this.add(20, OptionLevel.RARE, 303)
        this.add(21, OptionLevel.RARE, 303)
        this.add(22, OptionLevel.RARE, 0)
        this.add(23, OptionLevel.RARE, 0)
        this.add(24, OptionLevel.RARE, 0)
        this.add(25, OptionLevel.RARE, 0)
        this.add(26, OptionLevel.RARE, 346)
        this.add(27, OptionLevel.RARE, 346)
        this.add(28, OptionLevel.RARE, 346)
        this.add(29, OptionLevel.RARE, 389)
        this.add(30, OptionLevel.RARE, 389)
        this.add(31, OptionLevel.RARE, 389)
        this.add(32, OptionLevel.RARE, 389)
        this.add(33, OptionLevel.RARE, 389)
        this.add(34, OptionLevel.RARE, 389)
        this.add(35, OptionLevel.RARE, 389)
        this.add(36, OptionLevel.RARE, 389)
        this.add(37, OptionLevel.RARE, 389)
        this.add(38, OptionLevel.RARE, 389)
        this.add(39, OptionLevel.RARE, 389)
        this.add(40, OptionLevel.RARE, 389)

        // EPIC
        this.add(0, OptionLevel.EPIC, 406)
        this.add(1, OptionLevel.EPIC, 406)
        this.add(2, OptionLevel.EPIC, 406)
        this.add(3, OptionLevel.EPIC, 406)
        this.add(4, OptionLevel.EPIC, 271)
        this.add(5, OptionLevel.EPIC, 271)
        this.add(6, OptionLevel.EPIC, 271)
        this.add(7, OptionLevel.EPIC, 271)
        this.add(8, OptionLevel.EPIC, 180)
        this.add(9, OptionLevel.EPIC, 271)
        this.add(10, OptionLevel.EPIC, 0)
        this.add(11, OptionLevel.EPIC, 271)
        this.add(12, OptionLevel.EPIC, 271)
        this.add(13, OptionLevel.EPIC, 271)
        this.add(14, OptionLevel.EPIC, 271)
        this.add(15, OptionLevel.EPIC, 0)
        this.add(16, OptionLevel.EPIC, 0)
        this.add(17, OptionLevel.EPIC, 0)
        this.add(18, OptionLevel.EPIC, 0)
        this.add(19, OptionLevel.EPIC, 0)
        this.add(20, OptionLevel.EPIC, 271)
        this.add(21, OptionLevel.EPIC, 271)
        this.add(22, OptionLevel.EPIC, 0)
        this.add(23, OptionLevel.EPIC, 0)
        this.add(24, OptionLevel.EPIC, 0)
        this.add(25, OptionLevel.EPIC, 0)
        this.add(26, OptionLevel.EPIC, 135)
        this.add(27, OptionLevel.EPIC, 271)
        this.add(28, OptionLevel.EPIC, 271)
        this.add(29, OptionLevel.EPIC, 379)
        this.add(30, OptionLevel.EPIC, 379)
        this.add(31, OptionLevel.EPIC, 379)
        this.add(32, OptionLevel.EPIC, 379)
        this.add(33, OptionLevel.EPIC, 379)
        this.add(34, OptionLevel.EPIC, 379)
        this.add(35, OptionLevel.EPIC, 379)
        this.add(36, OptionLevel.EPIC, 379)
        this.add(37, OptionLevel.EPIC, 379)
        this.add(38, OptionLevel.EPIC, 379)
        this.add(39, OptionLevel.EPIC, 379)
        this.add(40, OptionLevel.EPIC, 379)

        // UNIQUE
        this.add(0, OptionLevel.UNIQUE, 407)
        this.add(1, OptionLevel.UNIQUE, 407)
        this.add(2, OptionLevel.UNIQUE, 407)
        this.add(3, OptionLevel.UNIQUE, 407)
        this.add(4, OptionLevel.UNIQUE, 226)
        this.add(5, OptionLevel.UNIQUE, 226)
        this.add(6, OptionLevel.UNIQUE, 226)
        this.add(7, OptionLevel.UNIQUE, 226)
        this.add(8, OptionLevel.UNIQUE, 90)
        this.add(9, OptionLevel.UNIQUE, 181)
        this.add(10, OptionLevel.UNIQUE, 0)
        this.add(11, OptionLevel.UNIQUE, 271)
        this.add(12, OptionLevel.UNIQUE, 271)
        this.add(13, OptionLevel.UNIQUE, 271)
        this.add(14, OptionLevel.UNIQUE, 271)
        this.add(15, OptionLevel.UNIQUE, 0)
        this.add(16, OptionLevel.UNIQUE, 0)
        this.add(17, OptionLevel.UNIQUE, 181)
        this.add(18, OptionLevel.UNIQUE, 181)
        this.add(19, OptionLevel.UNIQUE, 226)
        this.add(20, OptionLevel.UNIQUE, 181)
        this.add(21, OptionLevel.UNIQUE, 181)
        this.add(22, OptionLevel.UNIQUE, 181)
        this.add(23, OptionLevel.UNIQUE, 181)
        this.add(24, OptionLevel.UNIQUE, 0)
        this.add(25, OptionLevel.UNIQUE, 0)
        this.add(26, OptionLevel.UNIQUE, 90)
        this.add(27, OptionLevel.UNIQUE, 181)
        this.add(28, OptionLevel.UNIQUE, 181)
        this.add(29, OptionLevel.UNIQUE, 362)
        this.add(30, OptionLevel.UNIQUE, 362)
        this.add(31, OptionLevel.UNIQUE, 362)
        this.add(32, OptionLevel.UNIQUE, 362)
        this.add(33, OptionLevel.UNIQUE, 362)
        this.add(34, OptionLevel.UNIQUE, 362)
        this.add(35, OptionLevel.UNIQUE, 362)
        this.add(36, OptionLevel.UNIQUE, 362)
        this.add(37, OptionLevel.UNIQUE, 362)
        this.add(38, OptionLevel.UNIQUE, 362)
        this.add(39, OptionLevel.UNIQUE, 362)
        this.add(40, OptionLevel.UNIQUE, 362)

        // LEGENDARY
        this.add(0, OptionLevel.LEGENDARY, 416)
        this.add(1, OptionLevel.LEGENDARY, 416)
        this.add(2, OptionLevel.LEGENDARY, 416)
        this.add(3, OptionLevel.LEGENDARY, 416)
        this.add(4, OptionLevel.LEGENDARY, 185)
        this.add(5, OptionLevel.LEGENDARY, 185)
        this.add(6, OptionLevel.LEGENDARY, 231)
        this.add(7, OptionLevel.LEGENDARY, 231)
        this.add(8, OptionLevel.LEGENDARY, 46)
        this.add(9, OptionLevel.LEGENDARY, 185)
        this.add(10, OptionLevel.LEGENDARY, 46)
        this.add(11, OptionLevel.LEGENDARY, 231)
        this.add(12, OptionLevel.LEGENDARY, 231)
        this.add(13, OptionLevel.LEGENDARY, 231)
        this.add(14, OptionLevel.LEGENDARY, 231)
        this.add(15, OptionLevel.LEGENDARY, 231)
        this.add(16, OptionLevel.LEGENDARY, 231)
        this.add(17, OptionLevel.LEGENDARY, 185)
        this.add(18, OptionLevel.LEGENDARY, 185)
        this.add(19, OptionLevel.LEGENDARY, 231)
        this.add(20, OptionLevel.LEGENDARY, 185)
        this.add(21, OptionLevel.LEGENDARY, 185)
        this.add(22, OptionLevel.LEGENDARY, 185)
        this.add(23, OptionLevel.LEGENDARY, 185)
        this.add(24, OptionLevel.LEGENDARY, 74)
        this.add(25, OptionLevel.LEGENDARY, 74)
        this.add(26, OptionLevel.LEGENDARY, 93)
        this.add(27, OptionLevel.LEGENDARY, 185)
        this.add(28, OptionLevel.LEGENDARY, 185)
        this.add(29, OptionLevel.LEGENDARY, 324)
        this.add(30, OptionLevel.LEGENDARY, 324)
        this.add(31, OptionLevel.LEGENDARY, 324)
        this.add(32, OptionLevel.LEGENDARY, 324)
        this.add(33, OptionLevel.LEGENDARY, 324)
        this.add(34, OptionLevel.LEGENDARY, 324)
        this.add(35, OptionLevel.LEGENDARY, 324)
        this.add(36, OptionLevel.LEGENDARY, 324)
        this.add(37, OptionLevel.LEGENDARY, 324)
        this.add(38, OptionLevel.LEGENDARY, 324)
        this.add(39, OptionLevel.LEGENDARY, 324)
        this.add(40, OptionLevel.LEGENDARY, 324)
    }

    private fun add(
        abilityWeight: AbilityWeight
    ) {
        this.tableByRare[abilityWeight.level] = this.tableByRare[abilityWeight.level]?.plus(abilityWeight) ?: listOf(abilityWeight)
    }

    fun add(
        id: Int,
        level: OptionLevel,
        weight: Int
    ) {
        if (weight <= 0) return

        val option = this.abilityOptionRepository.get(id)
        this.add(AbilityWeight(level, weight, option))
    }

    fun getItems(
        rarity: OptionLevel,
        withoutOptions: List<AbilityOption> = listOf()
    ): List<AbilityWeight> {
        val withoutIds = withoutOptions.map { it.id }

        return this.tableByRare[rarity]?.filterNot { withoutIds.contains(it.option.id) } ?: listOf()
    }
}
