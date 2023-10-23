package git.shrimp.maple_helper.core.ability.service

import git.shrimp.maple_helper.core.ability.data.AbilityNumericRepository
import git.shrimp.maple_helper.core.ability.data.AbilityOptionRepository
import git.shrimp.maple_helper.core.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.core.ability.dto.AbilityResult
import git.shrimp.maple_helper.core.ability.dto.SimulationOption
import git.shrimp.maple_helper.core.ability.dto.SimulationResult
import git.shrimp.maple_helper.core.ability.dto.TargetDto
import git.shrimp.maple_helper.core.ability.model.AbilityMode
import git.shrimp.maple_helper.core.ability.model.AbilityWeight
import git.shrimp.maple_helper.core.ability.service.MapleAbilityService
import git.shrimp.maple_helper.core.global.model.OptionLevel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.scheduling.annotation.Async

@OptIn(ExperimentalCoroutinesApi::class)
open class MapleAbilityServiceTest {
    private val abilityOptionRepository = AbilityOptionRepository()
    private val abilityWeightRepository = AbilityWeightRepository(abilityOptionRepository)
    private val abilityNumericRepository = AbilityNumericRepository(abilityOptionRepository)
    private val mapleAbilityService = MapleAbilityService(abilityOptionRepository, abilityWeightRepository, abilityNumericRepository)

    @Test
    fun legendaryTest() {
        println("========================================")
        for (index in 0..100) {
            val options = this.mapleAbilityService.getOption(mainLevel = OptionLevel.LEGENDARY, mode = AbilityMode.MIRACLE)
            options.forEach(::println)
            println("========================================")
        }
    }

    @Test
    fun uniqueTest() {
        println("========================================")
        for (index in 0..100) {
            val options = this.mapleAbilityService.getOption(mainLevel = OptionLevel.UNIQUE)
            options.forEach(::println)
            println("========================================")
        }
    }

    @Test
    fun simulationTest() = runTest {
        val targetDto = TargetDto(8, OptionLevel.UNIQUE, arrayOf(20))
        val option = SimulationOption(10000, OptionLevel.LEGENDARY, AbilityMode.MIRACLE)

        val simulationResults = mapleAbilityService.simulation(targetDto, option)

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
            val msg = "count: %4d ~ %4d => %4d (%.2f) %4d (%.2f)".format(
                index, index + diff - 1,
                nowCount, nowCount.toDouble() / total * 100,
                accCount, accCount.toDouble() / total * 100
            )
            println(msg)
        }
    }
}
