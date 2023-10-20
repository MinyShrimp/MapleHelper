package git.shrimp.maple_helper.ability.data

import git.shrimp.maple_helper.ability.model.AbilityOption
import git.shrimp.maple_helper.ability.model.AbilityWeight
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AbilityWeightRepository(
    private val abilityOptionRepository: AbilityOptionRepository
) {
    private val table = mutableMapOf<UUID, AbilityWeight>()

    init {
        // RARE
        this.add(0, AbilityWeight.OptionLevel.RARE, 389)
        this.add(1, AbilityWeight.OptionLevel.RARE, 389)
        this.add(2, AbilityWeight.OptionLevel.RARE, 389)
        this.add(3, AbilityWeight.OptionLevel.RARE, 389)
        this.add(4, AbilityWeight.OptionLevel.RARE, 372)
        this.add(5, AbilityWeight.OptionLevel.RARE, 372)
        this.add(6, AbilityWeight.OptionLevel.RARE, 0)
        this.add(7, AbilityWeight.OptionLevel.RARE, 0)
        this.add(8, AbilityWeight.OptionLevel.RARE, 0)
        this.add(9, AbilityWeight.OptionLevel.RARE, 346)
        this.add(10, AbilityWeight.OptionLevel.RARE, 0)
        this.add(11, AbilityWeight.OptionLevel.RARE, 260)
        this.add(12, AbilityWeight.OptionLevel.RARE, 260)
        this.add(13, AbilityWeight.OptionLevel.RARE, 260)
        this.add(14, AbilityWeight.OptionLevel.RARE, 260)
        this.add(15, AbilityWeight.OptionLevel.RARE, 0)
        this.add(16, AbilityWeight.OptionLevel.RARE, 0)
        this.add(17, AbilityWeight.OptionLevel.RARE, 0)
        this.add(18, AbilityWeight.OptionLevel.RARE, 0)
        this.add(19, AbilityWeight.OptionLevel.RARE, 0)
        this.add(20, AbilityWeight.OptionLevel.RARE, 303)
        this.add(21, AbilityWeight.OptionLevel.RARE, 303)
        this.add(22, AbilityWeight.OptionLevel.RARE, 0)
        this.add(23, AbilityWeight.OptionLevel.RARE, 0)
        this.add(24, AbilityWeight.OptionLevel.RARE, 0)
        this.add(25, AbilityWeight.OptionLevel.RARE, 0)
        this.add(26, AbilityWeight.OptionLevel.RARE, 346)
        this.add(27, AbilityWeight.OptionLevel.RARE, 346)
        this.add(28, AbilityWeight.OptionLevel.RARE, 346)
        this.add(29, AbilityWeight.OptionLevel.RARE, 389)
        this.add(30, AbilityWeight.OptionLevel.RARE, 389)
        this.add(31, AbilityWeight.OptionLevel.RARE, 389)
        this.add(32, AbilityWeight.OptionLevel.RARE, 389)
        this.add(33, AbilityWeight.OptionLevel.RARE, 389)
        this.add(34, AbilityWeight.OptionLevel.RARE, 389)
        this.add(35, AbilityWeight.OptionLevel.RARE, 389)
        this.add(36, AbilityWeight.OptionLevel.RARE, 389)
        this.add(37, AbilityWeight.OptionLevel.RARE, 389)
        this.add(38, AbilityWeight.OptionLevel.RARE, 389)
        this.add(39, AbilityWeight.OptionLevel.RARE, 389)
        this.add(40, AbilityWeight.OptionLevel.RARE, 389)

        // EPIC
        this.add(0,  AbilityWeight.OptionLevel.EPIC, 406)
        this.add(1,  AbilityWeight.OptionLevel.EPIC, 406)
        this.add(2,  AbilityWeight.OptionLevel.EPIC, 406)
        this.add(3,  AbilityWeight.OptionLevel.EPIC, 406)
        this.add(4,  AbilityWeight.OptionLevel.EPIC, 271)
        this.add(5,  AbilityWeight.OptionLevel.EPIC, 271)
        this.add(6,  AbilityWeight.OptionLevel.EPIC, 271)
        this.add(7,  AbilityWeight.OptionLevel.EPIC, 271)
        this.add(8,  AbilityWeight.OptionLevel.EPIC, 180)
        this.add(9,  AbilityWeight.OptionLevel.EPIC, 271)
        this.add(10, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(11, AbilityWeight.OptionLevel.EPIC, 271)
        this.add(12, AbilityWeight.OptionLevel.EPIC, 271)
        this.add(13, AbilityWeight.OptionLevel.EPIC, 271)
        this.add(14, AbilityWeight.OptionLevel.EPIC, 271)
        this.add(15, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(16, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(17, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(18, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(19, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(20, AbilityWeight.OptionLevel.EPIC, 271)
        this.add(21, AbilityWeight.OptionLevel.EPIC, 271)
        this.add(22, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(23, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(24, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(25, AbilityWeight.OptionLevel.EPIC, 0)
        this.add(26, AbilityWeight.OptionLevel.EPIC, 135)
        this.add(27, AbilityWeight.OptionLevel.EPIC, 271)
        this.add(28, AbilityWeight.OptionLevel.EPIC, 271)
        this.add(29, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(30, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(31, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(32, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(33, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(34, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(35, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(36, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(37, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(38, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(39, AbilityWeight.OptionLevel.EPIC, 379)
        this.add(40, AbilityWeight.OptionLevel.EPIC, 379)

        // UNIQUE
        this.add(0,  AbilityWeight.OptionLevel.UNIQUE, 407)
        this.add(1,  AbilityWeight.OptionLevel.UNIQUE, 407)
        this.add(2,  AbilityWeight.OptionLevel.UNIQUE, 407)
        this.add(3,  AbilityWeight.OptionLevel.UNIQUE, 407)
        this.add(4,  AbilityWeight.OptionLevel.UNIQUE, 226)
        this.add(5,  AbilityWeight.OptionLevel.UNIQUE, 226)
        this.add(6,  AbilityWeight.OptionLevel.UNIQUE, 226)
        this.add(7,  AbilityWeight.OptionLevel.UNIQUE, 226)
        this.add(8,  AbilityWeight.OptionLevel.UNIQUE, 90)
        this.add(9,  AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(10, AbilityWeight.OptionLevel.UNIQUE, 0)
        this.add(11, AbilityWeight.OptionLevel.UNIQUE, 271)
        this.add(12, AbilityWeight.OptionLevel.UNIQUE, 271)
        this.add(13, AbilityWeight.OptionLevel.UNIQUE, 271)
        this.add(14, AbilityWeight.OptionLevel.UNIQUE, 271)
        this.add(15, AbilityWeight.OptionLevel.UNIQUE, 0)
        this.add(16, AbilityWeight.OptionLevel.UNIQUE, 0)
        this.add(17, AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(18, AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(19, AbilityWeight.OptionLevel.UNIQUE, 226)
        this.add(20, AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(21, AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(22, AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(23, AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(24, AbilityWeight.OptionLevel.UNIQUE, 0)
        this.add(25, AbilityWeight.OptionLevel.UNIQUE, 0)
        this.add(26, AbilityWeight.OptionLevel.UNIQUE, 90)
        this.add(27, AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(28, AbilityWeight.OptionLevel.UNIQUE, 181)
        this.add(29, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(30, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(31, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(32, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(33, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(34, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(35, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(36, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(37, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(38, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(39, AbilityWeight.OptionLevel.UNIQUE, 362)
        this.add(40, AbilityWeight.OptionLevel.UNIQUE, 362)

        // LEGENDARY
        this.add(0,  AbilityWeight.OptionLevel.LEGENDARY, 416)
        this.add(1,  AbilityWeight.OptionLevel.LEGENDARY, 416)
        this.add(2,  AbilityWeight.OptionLevel.LEGENDARY, 416)
        this.add(3,  AbilityWeight.OptionLevel.LEGENDARY, 416)
        this.add(4,  AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(5,  AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(6,  AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(7,  AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(8,  AbilityWeight.OptionLevel.LEGENDARY, 46)
        this.add(9,  AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(10, AbilityWeight.OptionLevel.LEGENDARY, 46)
        this.add(11, AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(12, AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(13, AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(14, AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(15, AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(16, AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(17, AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(18, AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(19, AbilityWeight.OptionLevel.LEGENDARY, 231)
        this.add(20, AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(21, AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(22, AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(23, AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(24, AbilityWeight.OptionLevel.LEGENDARY, 74)
        this.add(25, AbilityWeight.OptionLevel.LEGENDARY, 74)
        this.add(26, AbilityWeight.OptionLevel.LEGENDARY, 93)
        this.add(27, AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(28, AbilityWeight.OptionLevel.LEGENDARY, 185)
        this.add(29, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(30, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(31, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(32, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(33, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(34, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(35, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(36, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(37, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(38, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(39, AbilityWeight.OptionLevel.LEGENDARY, 324)
        this.add(40, AbilityWeight.OptionLevel.LEGENDARY, 324)
    }

    fun add(
        abilityWeight: AbilityWeight
    ) {
        table[abilityWeight.id] = abilityWeight
    }

    fun add(
        id: Int,
        level: AbilityWeight.OptionLevel,
        weight: Int
    ) {
        val option = this.abilityOptionRepository.get(id)
        this.add(AbilityWeight(level, weight, option))
    }

    fun getItems(
        rarity: AbilityWeight.OptionLevel? = null,
        withoutOptions: List<AbilityOption> = listOf()
    ): List<AbilityWeight> {
        val withoutIds = withoutOptions.map { it.id }

        return if(rarity == null) {
            table.values.filter { !withoutIds.contains(it.option.id) }.toList()
        } else {
            table.values.filter { !withoutIds.contains(it.option.id) }.filter { it.level == rarity }.toList()
        }
    }
}
