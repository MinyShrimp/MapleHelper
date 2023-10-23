package git.shrimp.maple_helper.core.ability.data

import git.shrimp.maple_helper.core.ability.model.AbilityNumeric
import git.shrimp.maple_helper.core.global.model.OptionLevel
import org.springframework.stereotype.Service
import java.util.*

@Service
class AbilityNumericRepository(
    private val abilityOptionRepository: AbilityOptionRepository
) {
    private val table = mutableMapOf<UUID, AbilityNumeric>()

    init {
        ////////////////////////////////////////////////////////////////
        // [[5], [6], [7], [8], [9], [10]]
        this.add(0, OptionLevel.RARE, Array(6) { arrayOf(it + 5) })
        this.add(1, OptionLevel.RARE, Array(6) { arrayOf(it + 5) })
        this.add(2, OptionLevel.RARE, Array(6) { arrayOf(it + 5) })
        this.add(3, OptionLevel.RARE, Array(6) { arrayOf(it + 5) })
        this.add(9, OptionLevel.RARE, Array(6) { arrayOf(it + 5) })

        // [[75], [90], [105], [120], [135], [150]]
        this.add(4, OptionLevel.RARE, Array(6) { arrayOf(75 + 15 * it) })
        this.add(5, OptionLevel.RARE, Array(6) { arrayOf(75 + 15 * it) })

        // [[2], [2], [2], [2], [3], [3]]
        this.add(11, OptionLevel.RARE, Array(4) { arrayOf(2) } + Array(2) { arrayOf(3) })
        this.add(12, OptionLevel.RARE, Array(4) { arrayOf(2) } + Array(2) { arrayOf(3) })
        this.add(13, OptionLevel.RARE, Array(4) { arrayOf(2) } + Array(2) { arrayOf(3) })
        this.add(14, OptionLevel.RARE, Array(4) { arrayOf(2) } + Array(2) { arrayOf(3) })
        this.add(20, OptionLevel.RARE, Array(4) { arrayOf(2) } + Array(2) { arrayOf(3) })
        this.add(21, OptionLevel.RARE, Array(4) { arrayOf(2) } + Array(2) { arrayOf(3) })

        // [[7], [8], [9], [10], [12], [13]]
        this.add(26, OptionLevel.RARE, Array(4) { arrayOf(7 + it) } + Array(2) { arrayOf(12 + it) })

        // [[3], [3], [4], [4], [5], [5]]
        this.add(27, OptionLevel.RARE, Array(6) { arrayOf(3 + it / 2) })
        this.add(28, OptionLevel.RARE, Array(6) { arrayOf(3 + it / 2) })

        // [[5, 3], [6, 3], [7, 4], [8, 4], [9, 5], [10, 5]]
        this.add(29, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(30, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(31, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(32, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(33, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(34, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(35, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(36, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(37, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(38, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(39, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })
        this.add(40, OptionLevel.RARE, Array(6) { arrayOf(it + 5, 3 + it / 2) })

        ////////////////////////////////////////////////////////////////
        // [[15], [16], [17], [18], [19], [20]]
        this.add(0, OptionLevel.EPIC, Array(6) { arrayOf(it + 15) })
        this.add(1, OptionLevel.EPIC, Array(6) { arrayOf(it + 15) })
        this.add(2, OptionLevel.EPIC, Array(6) { arrayOf(it + 15) })
        this.add(3, OptionLevel.EPIC, Array(6) { arrayOf(it + 15) })
        this.add(9, OptionLevel.EPIC, Array(6) { arrayOf(it + 15) })

        // [[225], [240], [255], [270], [285], [300]]
        this.add(4, OptionLevel.EPIC, Array(6) { arrayOf(225 + 15 * it) })
        this.add(5, OptionLevel.EPIC, Array(6) { arrayOf(225 + 15 * it) })

        // [[6], [6], [9], [9], [9], [12]]
        this.add(6, OptionLevel.EPIC, Array(2) { arrayOf(6) } + Array(3) { arrayOf(9) } + Array(1) { arrayOf(12) })
        this.add(7, OptionLevel.EPIC, Array(2) { arrayOf(6) } + Array(3) { arrayOf(9) } + Array(1) { arrayOf(12) })

        // [[5], [6], [7], [8], [9], [10]]
        this.add(8, OptionLevel.EPIC, Array(6) { arrayOf(it + 5) })

        // [[4], [4], [5], [5], [5], [5]]
        this.add(11, OptionLevel.EPIC, Array(2) { arrayOf(4) } + Array(4) { arrayOf(5) })
        this.add(12, OptionLevel.EPIC, Array(2) { arrayOf(4) } + Array(4) { arrayOf(5) })
        this.add(13, OptionLevel.EPIC, Array(2) { arrayOf(4) } + Array(4) { arrayOf(5) })
        this.add(14, OptionLevel.EPIC, Array(2) { arrayOf(4) } + Array(4) { arrayOf(5) })
        this.add(20, OptionLevel.EPIC, Array(2) { arrayOf(4) } + Array(4) { arrayOf(5) })
        this.add(21, OptionLevel.EPIC, Array(2) { arrayOf(4) } + Array(4) { arrayOf(5) })

        // [[19], [20], [22], [23], [24], [25]]
        this.add(26, OptionLevel.EPIC, Array(2) { arrayOf(19 + it) } + Array(4) { arrayOf(22 + it) })

        // [[8], [8], [9], [9], [10], [10]]
        this.add(27, OptionLevel.EPIC, Array(6) { arrayOf(8 + it / 2) })
        this.add(28, OptionLevel.EPIC, Array(6) { arrayOf(8 + it / 2) })

        // [[15, 8], [16, 8], [17, 9], [18, 9], [19, 10], [20, 10]]
        this.add(29, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(30, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(31, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(32, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(33, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(34, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(35, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(36, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(37, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(38, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(39, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })
        this.add(40, OptionLevel.EPIC, Array(6) { arrayOf(it + 15, 8 + it / 2) })

        ////////////////////////////////////////////////////////////////
        // [[25], [26], [27], [28], [29], [30]]
        this.add(0, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25) })
        this.add(1, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25) })
        this.add(2, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25) })
        this.add(3, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25) })
        this.add(9, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25) })

        // [[375], [390], [405], [420], [435], [450]]
        this.add(4, OptionLevel.UNIQUE, Array(6) { arrayOf(375 + 15 * it) })
        this.add(5, OptionLevel.UNIQUE, Array(6) { arrayOf(375 + 15 * it) })

        // [[15], [18], [18], [18], [21], [21]]
        this.add(6, OptionLevel.UNIQUE, Array(1) { arrayOf(15) } + Array(3) { arrayOf(18) } + Array(2) { arrayOf(21) })
        this.add(7, OptionLevel.UNIQUE, Array(1) { arrayOf(15) } + Array(3) { arrayOf(18) } + Array(2) { arrayOf(21) })

        // [[15], [16], [17], [18], [19], [20]]
        this.add(8, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 15) })

        // [[7], [7], [7], [7], [8], [8]]
        this.add(11, OptionLevel.UNIQUE, Array(4) { arrayOf(7) } + Array(2) { arrayOf(8) })
        this.add(12, OptionLevel.UNIQUE, Array(4) { arrayOf(7) } + Array(2) { arrayOf(8) })
        this.add(13, OptionLevel.UNIQUE, Array(4) { arrayOf(7) } + Array(2) { arrayOf(8) })
        this.add(14, OptionLevel.UNIQUE, Array(4) { arrayOf(7) } + Array(2) { arrayOf(8) })
        this.add(20, OptionLevel.UNIQUE, Array(4) { arrayOf(7) } + Array(2) { arrayOf(8) })
        this.add(21, OptionLevel.UNIQUE, Array(4) { arrayOf(7) } + Array(2) { arrayOf(8) })

        // [[5], [6], [7], [8], [9], [10]]
        this.add(17, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 5) })
        this.add(18, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 5) })
        this.add(19, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 5) })
        this.add(23, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 5) })

        // [[13], [15], [18], [20], [23], [25]]
        this.add(22, OptionLevel.UNIQUE, Array(2) { arrayOf(13 + it * 2) } + Array(2) { arrayOf(18 + it * 2) } + Array(2) { arrayOf(23 + it * 2) })

        // [[32], [33], [34], [35], [37], [38]]
        this.add(26, OptionLevel.UNIQUE, Array(4) { arrayOf(32 + it) } + Array(2) { arrayOf(37 + it) })

        // [[13], [13], [14], [14], [15], [15]]
        this.add(27, OptionLevel.UNIQUE, Array(6) { arrayOf(13 + it / 2) })
        this.add(28, OptionLevel.UNIQUE, Array(6) { arrayOf(13 + it / 2) })

        // [[25, 13], [26, 13], [27, 14], [28, 14], [29, 15], [30, 15]]
        this.add(29, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(30, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(31, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(32, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(33, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(34, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(35, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(36, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(37, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(38, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(39, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })
        this.add(40, OptionLevel.UNIQUE, Array(6) { arrayOf(it + 25, 13 + it / 2) })

        ////////////////////////////////////////////////////////////////
        // [[35], [36], [37], [38], [39], [40]]
        this.add(0, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35) })
        this.add(1, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35) })
        this.add(2, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35) })
        this.add(3, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35) })
        this.add(9, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35) })

        // [[525], [540], [555], [570], [585], [600]]
        this.add(4, OptionLevel.LEGENDARY, Array(6) { arrayOf(525 + 15 * it) })
        this.add(5, OptionLevel.LEGENDARY, Array(6) { arrayOf(525 + 15 * it) })

        // [[27], [27], [27], [30], [30], [30]]
        this.add(6, OptionLevel.LEGENDARY, Array(3) { arrayOf(27) } + Array(3) { arrayOf(30) })
        this.add(7, OptionLevel.LEGENDARY, Array(3) { arrayOf(27) } + Array(3) { arrayOf(30) })

        // [[25], [26], [27], [28], [29], [30]]
        this.add(8, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 25) })

        // [[9], [9], [10], [10], [10], [10]]
        this.add(11, OptionLevel.LEGENDARY, Array(2) { arrayOf(9) } + Array(4) { arrayOf(10) })
        this.add(12, OptionLevel.LEGENDARY, Array(2) { arrayOf(9) } + Array(4) { arrayOf(10) })
        this.add(13, OptionLevel.LEGENDARY, Array(2) { arrayOf(9) } + Array(4) { arrayOf(10) })
        this.add(14, OptionLevel.LEGENDARY, Array(2) { arrayOf(9) } + Array(4) { arrayOf(10) })
        this.add(20, OptionLevel.LEGENDARY, Array(2) { arrayOf(9) } + Array(4) { arrayOf(10) })
        this.add(21, OptionLevel.LEGENDARY, Array(2) { arrayOf(9) } + Array(4) { arrayOf(10) })

        // [[16], [14], [14], [12], [12], [10]]
        this.add(15, OptionLevel.LEGENDARY, Array(1) { arrayOf(16) } + Array(2) { arrayOf(14) } + Array(2) { arrayOf(12) } + Array(1) { arrayOf(10) })
        this.add(16, OptionLevel.LEGENDARY, Array(1) { arrayOf(16) } + Array(2) { arrayOf(14) } + Array(2) { arrayOf(12) } + Array(1) { arrayOf(10) })

        // [[15], [16], [17], [18], [19], [20]]
        this.add(17, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 15) })
        this.add(18, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 15) })
        this.add(19, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 15) })
        this.add(23, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 15) })

        // [[38], [40], [43], [45], [48], [50]]
        this.add(22, OptionLevel.LEGENDARY, Array(2) { arrayOf(38 + it * 2) } + Array(2) { arrayOf(43 + it * 2) } + Array(2) { arrayOf(48 + it * 2) })

        // [[1], [1], [1], [1], [1], [1]]
        this.add(10, OptionLevel.LEGENDARY, Array(6) { arrayOf(1) })
        this.add(24, OptionLevel.LEGENDARY, Array(6) { arrayOf(1) })
        this.add(25, OptionLevel.LEGENDARY, Array(6) { arrayOf(1) })

        // [[44], [45], [47], [48], [49], [50]]
        this.add(26, OptionLevel.LEGENDARY, Array(2) { arrayOf(44 + it) } + Array(4) { arrayOf(47 + it) })

        // [[18], [18], [19], [19], [20], [20]]
        this.add(27, OptionLevel.LEGENDARY, Array(6) { arrayOf(18 + it / 2) })
        this.add(28, OptionLevel.LEGENDARY, Array(6) { arrayOf(18 + it / 2) })

        // [[35, 18], [36, 18], [37, 19], [38, 19], [39, 20], [40, 20]]
        this.add(29, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(30, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(31, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(32, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(33, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(34, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(35, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(36, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(37, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(38, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(39, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
        this.add(40, OptionLevel.LEGENDARY, Array(6) { arrayOf(it + 35, 18 + it / 2) })
    }

    private fun add(
        abilityNumeric: AbilityNumeric
    ) {
        this.table[abilityNumeric.id] = abilityNumeric
    }

    fun add(
        id: Int,
        level: OptionLevel,
        numerics: Array<Array<Int>>
    ) {
        val option = this.abilityOptionRepository.get(id)
        val weights = arrayOf(20, 20, 20, 15, 15, 10)

        numerics.zip(weights).forEach { (numeric, weight) ->
            this.add(AbilityNumeric(level, weight, option, numeric))
        }
    }

    fun getItems(
        id: Int,
        level: OptionLevel
    ): List<AbilityNumeric> {
        return this.table.values.filter { it.option.id == id && it.level == level }.toList()
    }
}
