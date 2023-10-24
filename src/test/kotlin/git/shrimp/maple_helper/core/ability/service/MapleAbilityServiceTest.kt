package git.shrimp.maple_helper.core.ability.service

import git.shrimp.maple_helper.core.ability.dto.AbilityResult
import git.shrimp.maple_helper.core.ability.dto.SimulationOption
import git.shrimp.maple_helper.core.ability.dto.TargetDto
import git.shrimp.maple_helper.core.ability.mock.AbilityNumericMockRepository
import git.shrimp.maple_helper.core.ability.mock.AbilityOptionMockRepository
import git.shrimp.maple_helper.core.ability.mock.AbilityWeightMockRepository
import git.shrimp.maple_helper.core.ability.model.AbilityMode
import git.shrimp.maple_helper.core.ability.repository.AbilityNumericRepository
import git.shrimp.maple_helper.core.ability.repository.AbilityOptionRepository
import git.shrimp.maple_helper.core.ability.repository.AbilityWeightRepository
import git.shrimp.maple_helper.core.global.model.OptionLevel
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

open class MapleAbilityServiceTest {
    private val abilityOptionRepository: AbilityOptionRepository = mockk()
    private val abilityWeightRepository: AbilityWeightRepository = mockk()
    private val abilityNumericRepository: AbilityNumericRepository = mockk()

    private val mapleAbilityService =
        MapleAbilityService(abilityOptionRepository, abilityWeightRepository, abilityNumericRepository)

    @BeforeEach
    fun beforeAll() {
        val abilityOptionMockRepository = AbilityOptionMockRepository()
        every {
            abilityOptionRepository.findById(any())
        } answers {
            abilityOptionMockRepository.findById(firstArg())
        }

        val abilityWeightMockRepository = AbilityWeightMockRepository(abilityOptionMockRepository)
        every {
            abilityWeightRepository.findAllByLevel(any())
        } answers {
            abilityWeightMockRepository.findAllByLevel(firstArg())
        }

        val abilityNumericMockRepository = AbilityNumericMockRepository(abilityOptionMockRepository)
        every {
            abilityNumericRepository.findAllByOptionIdAndLevel(any(), any())
        } answers {
            abilityNumericMockRepository.findAllByOptionIdAndLevel(firstArg(), secondArg())
        }
    }

    @Test
    fun legendaryTest() {
        println("========================================")
        for (index in 0..100) {
            val options =
                this.mapleAbilityService.getOption(mainLevel = OptionLevel.LEGENDARY, mode = AbilityMode.MIRACLE)
            options.forEach(::println)
            println("========================================")
        }
    }

    @Test
    fun uniqueTest() {
        println("========================================")
        for (index in 0..100) {
            val options = this.mapleAbilityService.getOption(
                mainLevel = OptionLevel.LEGENDARY,
                locks = listOf(AbilityResult(9, "크리티컬 확률 {0}% 증가", OptionLevel.UNIQUE, listOf(20)))
            )
            options.forEach(::println)
            println("========================================")
        }
    }

    @Test
    fun simulationTest() {
        val targetDto = TargetDto(9, OptionLevel.UNIQUE, listOf(20))
        val option = SimulationOption(10, OptionLevel.LEGENDARY, AbilityMode.MIRACLE)

        val simulationResults = mapleAbilityService.simulation(option, listOf(targetDto))

        val total = simulationResults.size
        val minCount = simulationResults.minOf { it.count }
        val maxCount = simulationResults.maxOf { it.count }
        val averageCount = simulationResults.sumOf { it.count } / total

        println("Final Result => Count: $total")
        println("min: $minCount / max: $maxCount / average: $averageCount")

        val diff = 100
        for (index in 0..maxCount step diff) {
            val nowCount = simulationResults.count { it.count in index until index + diff }
            val accCount = simulationResults.count { it.count < index + diff }
            val msg = "count: %4d ~ %4d => %4d (%2.2f) %4d (%2.2f)".format(
                index, index + diff - 1,
                nowCount, nowCount.toDouble() / total * 100,
                accCount, accCount.toDouble() / total * 100
            )
            println(msg)
        }
    }

    @Test
    fun simulationTest2() {
        val targets = listOf(
            TargetDto(29, OptionLevel.LEGENDARY, listOf(20))
        )
        val locks = listOf(
            TargetDto(9, OptionLevel.UNIQUE, listOf(20)),
            TargetDto(28, OptionLevel.UNIQUE, listOf(15))
        )
        val option = SimulationOption(10, OptionLevel.LEGENDARY, AbilityMode.MIRACLE)

        val simulationResults = mapleAbilityService.simulation(option, targets, locks)

        val total = simulationResults.size
        val minCount = simulationResults.minOf { it.count }
        val maxCount = simulationResults.maxOf { it.count }
        val averageCount = simulationResults.sumOf { it.count } / total

        println("Final Result => Total Try Counts: $total")
        println("min: $minCount / max: $maxCount / average: $averageCount")

        val diff = 100
        for (index in 0..maxCount step diff) {
            val nowCount = simulationResults.count { it.count in index until index + diff }
            val accCount = simulationResults.count { it.count < index + diff }
            val msg = "try count: %4d ~ %4d => %4d (%2.2f) %4d (%2.2f)".format(
                index, index + diff - 1,
                nowCount, nowCount.toDouble() / total * 100,
                accCount, accCount.toDouble() / total * 100
            )
            println(msg)
        }
    }
}
