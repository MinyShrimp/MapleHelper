package git.shrimp.maple_helper_api.ability.service.initialize

import git.shrimp.maple_helper_api.ability.entity.AbilityNumeric
import git.shrimp.maple_helper_api.ability.repository.AbilityNumericRepository
import git.shrimp.maple_helper_api.ability.repository.AbilityOptionRepository
import git.shrimp.maple_helper_core.global.model.OptionLevel
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilityNumericInitializeService(
    private val abilityOptionRepository: AbilityOptionRepository,
    private val abilityNumericRepository: AbilityNumericRepository
) {
    private fun add(
        id: Int,
        level: OptionLevel,
        numerics: List<List<Int>>
    ) {
        val option = this.abilityOptionRepository.findById(id).orElseThrow()
        val weights = listOf(20, 20, 20, 15, 15, 10)
        val entities = numerics.zip(weights).map { (numeric, weight) -> AbilityNumeric(level, weight, option, numeric) }

        this.abilityNumericRepository.saveAll(entities)
    }

    @Transactional
    fun initialize() {
        this.abilityNumericRepository.deleteAll()

        ////////////////////////////////////////////////////////////////
        // [[5], [6], [7], [8], [9], [10]]
        this.add(1, OptionLevel.RARE, List(6) { listOf(it + 5) })
        this.add(2, OptionLevel.RARE, List(6) { listOf(it + 5) })
        this.add(3, OptionLevel.RARE, List(6) { listOf(it + 5) })
        this.add(4, OptionLevel.RARE, List(6) { listOf(it + 5) })
        this.add(10, OptionLevel.RARE, List(6) { listOf(it + 5) })

        // [[75], [90], [105], [120], [135], [150]]
        this.add(5, OptionLevel.RARE, List(6) { listOf(75 + 15 * it) })
        this.add(6, OptionLevel.RARE, List(6) { listOf(75 + 15 * it) })

        // [[2], [2], [2], [2], [3], [3]]
        this.add(12, OptionLevel.RARE, List(4) { listOf(2) } + List(2) { listOf(3) })
        this.add(13, OptionLevel.RARE, List(4) { listOf(2) } + List(2) { listOf(3) })
        this.add(14, OptionLevel.RARE, List(4) { listOf(2) } + List(2) { listOf(3) })
        this.add(15, OptionLevel.RARE, List(4) { listOf(2) } + List(2) { listOf(3) })
        this.add(21, OptionLevel.RARE, List(4) { listOf(2) } + List(2) { listOf(3) })
        this.add(22, OptionLevel.RARE, List(4) { listOf(2) } + List(2) { listOf(3) })

        // [[7], [8], [9], [10], [12], [13]]
        this.add(27, OptionLevel.RARE, List(4) { listOf(7 + it) } + List(2) { listOf(12 + it) })

        // [[3], [3], [4], [4], [5], [5]]
        this.add(28, OptionLevel.RARE, List(6) { listOf(3 + it / 2) })
        this.add(29, OptionLevel.RARE, List(6) { listOf(3 + it / 2) })

        // [[5, 3], [6, 3], [7, 4], [8, 4], [9, 5], [10, 5]]
        this.add(30, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(31, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(32, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(33, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(34, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(35, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(36, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(37, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(38, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(39, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(40, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })
        this.add(41, OptionLevel.RARE, List(6) { listOf(it + 5, 3 + it / 2) })

        ////////////////////////////////////////////////////////////////
        // [[15], [16], [17], [18], [19], [20]]
        this.add(1, OptionLevel.EPIC, List(6) { listOf(it + 15) })
        this.add(2, OptionLevel.EPIC, List(6) { listOf(it + 15) })
        this.add(3, OptionLevel.EPIC, List(6) { listOf(it + 15) })
        this.add(4, OptionLevel.EPIC, List(6) { listOf(it + 15) })
        this.add(10, OptionLevel.EPIC, List(6) { listOf(it + 15) })

        // [[225], [240], [255], [270], [285], [300]]
        this.add(5, OptionLevel.EPIC, List(6) { listOf(225 + 15 * it) })
        this.add(6, OptionLevel.EPIC, List(6) { listOf(225 + 15 * it) })

        // [[6], [6], [9], [9], [9], [12]]
        this.add(7, OptionLevel.EPIC, List(2) { listOf(6) } + List(3) { listOf(9) } + List(1) { listOf(12) })
        this.add(8, OptionLevel.EPIC, List(2) { listOf(6) } + List(3) { listOf(9) } + List(1) { listOf(12) })

        // [[5], [6], [7], [8], [9], [10]]
        this.add(9, OptionLevel.EPIC, List(6) { listOf(it + 5) })

        // [[4], [4], [5], [5], [5], [5]]
        this.add(12, OptionLevel.EPIC, List(2) { listOf(4) } + List(4) { listOf(5) })
        this.add(13, OptionLevel.EPIC, List(2) { listOf(4) } + List(4) { listOf(5) })
        this.add(14, OptionLevel.EPIC, List(2) { listOf(4) } + List(4) { listOf(5) })
        this.add(15, OptionLevel.EPIC, List(2) { listOf(4) } + List(4) { listOf(5) })
        this.add(21, OptionLevel.EPIC, List(2) { listOf(4) } + List(4) { listOf(5) })
        this.add(22, OptionLevel.EPIC, List(2) { listOf(4) } + List(4) { listOf(5) })

        // [[19], [20], [22], [23], [24], [25]]
        this.add(27, OptionLevel.EPIC, List(2) { listOf(19 + it) } + List(4) { listOf(22 + it) })

        // [[8], [8], [9], [9], [10], [10]]
        this.add(28, OptionLevel.EPIC, List(6) { listOf(8 + it / 2) })
        this.add(29, OptionLevel.EPIC, List(6) { listOf(8 + it / 2) })

        // [[15, 8], [16, 8], [17, 9], [18, 9], [19, 10], [20, 10]]
        this.add(30, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(31, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(32, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(33, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(34, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(35, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(36, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(37, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(38, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(39, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(40, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })
        this.add(41, OptionLevel.EPIC, List(6) { listOf(it + 15, 8 + it / 2) })

        ////////////////////////////////////////////////////////////////
        // [[25], [26], [27], [28], [29], [30]]
        this.add(1, OptionLevel.UNIQUE, List(6) { listOf(it + 25) })
        this.add(2, OptionLevel.UNIQUE, List(6) { listOf(it + 25) })
        this.add(3, OptionLevel.UNIQUE, List(6) { listOf(it + 25) })
        this.add(4, OptionLevel.UNIQUE, List(6) { listOf(it + 25) })
        this.add(10, OptionLevel.UNIQUE, List(6) { listOf(it + 25) })

        // [[375], [390], [405], [420], [435], [450]]
        this.add(5, OptionLevel.UNIQUE, List(6) { listOf(375 + 15 * it) })
        this.add(6, OptionLevel.UNIQUE, List(6) { listOf(375 + 15 * it) })

        // [[15], [18], [18], [18], [21], [21]]
        this.add(7, OptionLevel.UNIQUE, List(1) { listOf(15) } + List(3) { listOf(18) } + List(2) { listOf(21) })
        this.add(8, OptionLevel.UNIQUE, List(1) { listOf(15) } + List(3) { listOf(18) } + List(2) { listOf(21) })

        // [[15], [16], [17], [18], [19], [20]]
        this.add(9, OptionLevel.UNIQUE, List(6) { listOf(it + 15) })

        // [[7], [7], [7], [7], [8], [8]]
        this.add(12, OptionLevel.UNIQUE, List(4) { listOf(7) } + List(2) { listOf(8) })
        this.add(13, OptionLevel.UNIQUE, List(4) { listOf(7) } + List(2) { listOf(8) })
        this.add(14, OptionLevel.UNIQUE, List(4) { listOf(7) } + List(2) { listOf(8) })
        this.add(15, OptionLevel.UNIQUE, List(4) { listOf(7) } + List(2) { listOf(8) })
        this.add(21, OptionLevel.UNIQUE, List(4) { listOf(7) } + List(2) { listOf(8) })
        this.add(22, OptionLevel.UNIQUE, List(4) { listOf(7) } + List(2) { listOf(8) })

        // [[5], [6], [7], [8], [9], [10]]
        this.add(18, OptionLevel.UNIQUE, List(6) { listOf(it + 5) })
        this.add(19, OptionLevel.UNIQUE, List(6) { listOf(it + 5) })
        this.add(20, OptionLevel.UNIQUE, List(6) { listOf(it + 5) })
        this.add(24, OptionLevel.UNIQUE, List(6) { listOf(it + 5) })

        // [[13], [15], [18], [20], [23], [25]]
        this.add(
            23, OptionLevel.UNIQUE,
            List(2) { listOf(13 + it * 2) } + List(2) { listOf(18 + it * 2) } + List(2) { listOf(23 + it * 2) }
        )

        // [[32], [33], [34], [35], [37], [38]]
        this.add(27, OptionLevel.UNIQUE, List(4) { listOf(32 + it) } + List(2) { listOf(37 + it) })

        // [[13], [13], [14], [14], [15], [15]]
        this.add(28, OptionLevel.UNIQUE, List(6) { listOf(13 + it / 2) })
        this.add(29, OptionLevel.UNIQUE, List(6) { listOf(13 + it / 2) })

        // [[25, 13], [26, 13], [27, 14], [28, 14], [29, 15], [30, 15]]
        this.add(30, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(31, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(32, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(33, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(34, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(35, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(36, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(37, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(38, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(39, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(40, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })
        this.add(41, OptionLevel.UNIQUE, List(6) { listOf(it + 25, 13 + it / 2) })

        ////////////////////////////////////////////////////////////////
        // [[35], [36], [37], [38], [39], [40]]
        this.add(1, OptionLevel.LEGENDARY, List(6) { listOf(it + 35) })
        this.add(2, OptionLevel.LEGENDARY, List(6) { listOf(it + 35) })
        this.add(3, OptionLevel.LEGENDARY, List(6) { listOf(it + 35) })
        this.add(4, OptionLevel.LEGENDARY, List(6) { listOf(it + 35) })
        this.add(10, OptionLevel.LEGENDARY, List(6) { listOf(it + 35) })

        // [[525], [540], [555], [570], [585], [600]]
        this.add(5, OptionLevel.LEGENDARY, List(6) { listOf(525 + 15 * it) })
        this.add(6, OptionLevel.LEGENDARY, List(6) { listOf(525 + 15 * it) })

        // [[27], [27], [27], [30], [30], [30]]
        this.add(7, OptionLevel.LEGENDARY, List(3) { listOf(27) } + List(3) { listOf(30) })
        this.add(8, OptionLevel.LEGENDARY, List(3) { listOf(27) } + List(3) { listOf(30) })

        // [[25], [26], [27], [28], [29], [30]]
        this.add(9, OptionLevel.LEGENDARY, List(6) { listOf(it + 25) })

        // [[9], [9], [10], [10], [10], [10]]
        this.add(12, OptionLevel.LEGENDARY, List(2) { listOf(9) } + List(4) { listOf(10) })
        this.add(13, OptionLevel.LEGENDARY, List(2) { listOf(9) } + List(4) { listOf(10) })
        this.add(14, OptionLevel.LEGENDARY, List(2) { listOf(9) } + List(4) { listOf(10) })
        this.add(15, OptionLevel.LEGENDARY, List(2) { listOf(9) } + List(4) { listOf(10) })
        this.add(21, OptionLevel.LEGENDARY, List(2) { listOf(9) } + List(4) { listOf(10) })
        this.add(22, OptionLevel.LEGENDARY, List(2) { listOf(9) } + List(4) { listOf(10) })

        // [[16], [14], [14], [12], [12], [10]]
        this.add(
            16, OptionLevel.LEGENDARY,
            List(1) { listOf(16) } + List(2) { listOf(14) } + List(2) { listOf(12) } + List(1) { listOf(10) }
        )
        this.add(
            17, OptionLevel.LEGENDARY,
            List(1) { listOf(16) } + List(2) { listOf(14) } + List(2) { listOf(12) } + List(1) { listOf(10) }
        )

        // [[15], [16], [17], [18], [19], [20]]
        this.add(18, OptionLevel.LEGENDARY, List(6) { listOf(it + 15) })
        this.add(19, OptionLevel.LEGENDARY, List(6) { listOf(it + 15) })
        this.add(20, OptionLevel.LEGENDARY, List(6) { listOf(it + 15) })
        this.add(24, OptionLevel.LEGENDARY, List(6) { listOf(it + 15) })

        // [[38], [40], [43], [45], [48], [50]]
        this.add(
            23, OptionLevel.LEGENDARY,
            List(2) { listOf(38 + it * 2) } + List(2) { listOf(43 + it * 2) } + List(2) { listOf(48 + it * 2) }
        )

        // [[1], [1], [1], [1], [1], [1]]
        this.add(11, OptionLevel.LEGENDARY, List(6) { listOf(1) })
        this.add(25, OptionLevel.LEGENDARY, List(6) { listOf(1) })
        this.add(26, OptionLevel.LEGENDARY, List(6) { listOf(1) })

        // [[44], [45], [47], [48], [49], [50]]
        this.add(27, OptionLevel.LEGENDARY, List(2) { listOf(44 + it) } + List(4) { listOf(47 + it) })

        // [[18], [18], [19], [19], [20], [20]]
        this.add(28, OptionLevel.LEGENDARY, List(6) { listOf(18 + it / 2) })
        this.add(29, OptionLevel.LEGENDARY, List(6) { listOf(18 + it / 2) })

        // [[35, 18], [36, 18], [37, 19], [38, 19], [39, 20], [40, 20]]
        this.add(30, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(31, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(32, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(33, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(34, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(35, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(36, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(37, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(38, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(39, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(40, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
        this.add(41, OptionLevel.LEGENDARY, List(6) { listOf(it + 35, 18 + it / 2) })
    }
}
