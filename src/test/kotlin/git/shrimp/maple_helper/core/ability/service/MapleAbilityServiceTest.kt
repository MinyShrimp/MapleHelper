package git.shrimp.maple_helper.core.ability.service

import git.shrimp.maple_helper.core.ability.data.AbilityNumericRepository
import git.shrimp.maple_helper.core.ability.data.AbilityOptionRepository
import git.shrimp.maple_helper.core.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.core.ability.dto.AbilityResult
import git.shrimp.maple_helper.core.ability.dto.SimulationOption
import git.shrimp.maple_helper.core.ability.dto.TargetDto
import git.shrimp.maple_helper.core.ability.model.AbilityMode
import git.shrimp.maple_helper.core.global.model.OptionLevel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
open class MapleAbilityServiceTest {
    private val abilityOptionRepository = AbilityOptionRepository()
    private val abilityWeightRepository = AbilityWeightRepository(abilityOptionRepository)
    private val abilityNumericRepository = AbilityNumericRepository(abilityOptionRepository)
    private val mapleAbilityService = MapleAbilityService(abilityOptionRepository, abilityWeightRepository, abilityNumericRepository)

    @Test
    fun legendaryTest() {
        println("========================================")
        for (index in 0..1000) {
            val options = this.mapleAbilityService.getOption(mainLevel = OptionLevel.LEGENDARY, mode = AbilityMode.MIRACLE)
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
                locks = listOf(AbilityResult(8, "크리티컬 확률 {0}% 증가", OptionLevel.UNIQUE, arrayOf(20)))
            )
            options.forEach(::println)
            println("========================================")
        }
    }

    @Test
    fun simulationTest() = runTest {
        val targetDto = TargetDto(8, OptionLevel.UNIQUE, arrayOf(20))
        val option = SimulationOption(10000, OptionLevel.LEGENDARY, AbilityMode.MIRACLE)

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
    fun simulationTest2() = runTest {
        val targets = listOf(
            TargetDto(28, OptionLevel.LEGENDARY, arrayOf(20))
        )
        val locks = listOf(
            TargetDto(8, OptionLevel.UNIQUE, arrayOf(20)),
            TargetDto(27, OptionLevel.UNIQUE, arrayOf(15))
        )
        val option = SimulationOption(1000, OptionLevel.LEGENDARY, AbilityMode.MIRACLE)

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
