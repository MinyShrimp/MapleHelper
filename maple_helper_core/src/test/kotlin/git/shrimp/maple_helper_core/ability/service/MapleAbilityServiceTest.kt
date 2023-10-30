package git.shrimp.maple_helper_core.ability.service

import git.shrimp.maple_helper_core.ability.dto.AbilityOption
import git.shrimp.maple_helper_core.ability.mock.AbilityOptionMockData
import git.shrimp.maple_helper_core.ability.mock.repository.AbilityNumericMockRepository
import git.shrimp.maple_helper_core.ability.mock.repository.AbilityOptionMockRepository
import git.shrimp.maple_helper_core.ability.mock.repository.AbilityWeightMockRepository
import git.shrimp.maple_helper_core.ability.types.AbilityMode
import git.shrimp.maple_helper_core.ability.types.OptionDataMap
import git.shrimp.maple_helper_core.global.types.OptionLevel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

open class MapleAbilityServiceTest {
    private val mapleAbilityService = MapleAbilityService()

    private val abilityOptionMockRepository = AbilityOptionMockRepository()
    private val abilityWeightMockRepository = AbilityWeightMockRepository(abilityOptionMockRepository)
    private val abilityNumericMockRepository = AbilityNumericMockRepository(abilityOptionMockRepository)
    private val abilityOptionMockData = AbilityOptionMockData(
        abilityOptionMockRepository,
        abilityWeightMockRepository,
        abilityNumericMockRepository
    )

    private val dataMap: OptionDataMap = abilityOptionMockData.get()

    //
//    private val mapleAbilitySimulationService = MapleAbilitySimulationService(
//        abilityOptionRepository,
//        mapleAbilityService
//    )
//
    @Test
    fun normalTest() = runBlocking {
        println("========================================")
        for (index in 0..100) {
            val result = mapleAbilityService.getOption(
                dataMap = dataMap,
                mainLevel = OptionLevel.LEGENDARY,
                mode = AbilityMode.NORMAL
            )
            result.entries.forEach(::println)
            println("========================================")
        }
    }

    @Test
    fun lockTest() = runBlocking {
        println("========================================")
        for (index in 0..100) {
            val result = mapleAbilityService.getOption(
                dataMap = dataMap,
                mainLevel = OptionLevel.LEGENDARY,
                locks = listOf(AbilityOption(9, OptionLevel.UNIQUE, listOf(20)))
            )
            result.entries.forEach(::println)
            println("========================================")
        }
    }
//
//    @Test
//    fun simulationTest() = runBlocking {
//        val targetDto = AbilityOptionDto(9, OptionLevel.UNIQUE, listOf(20))
//        val option = SimulationOption(10, OptionLevel.LEGENDARY, AbilityMode.MIRACLE)
//
//        val simulationResults = mapleAbilitySimulationService.simulation(option, listOf(targetDto))
//
//        val total = simulationResults.size
//        val minCount = simulationResults.minOf { it.count }
//        val maxCount = simulationResults.maxOf { it.count }
//        val averageCount = simulationResults.sumOf { it.count } / total
//
//        println("Final Result => Count: $total")
//        println("min: $minCount / max: $maxCount / average: $averageCount")
//
//        val diff = 100
//        for (index in 0..maxCount step diff) {
//            val nowCount = simulationResults.count { it.count in index until index + diff }
//            val accCount = simulationResults.count { it.count < index + diff }
//            val msg = "count: %4d ~ %4d => %4d (%2.2f) %4d (%2.2f)".format(
//                index, index + diff - 1,
//                nowCount, nowCount.toDouble() / total * 100,
//                accCount, accCount.toDouble() / total * 100
//            )
//            println(msg)
//        }
//    }
}
