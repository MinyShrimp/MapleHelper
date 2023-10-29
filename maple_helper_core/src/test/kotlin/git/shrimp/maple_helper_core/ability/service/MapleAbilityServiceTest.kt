package git.shrimp.maple_helper_core.ability.service

open class MapleAbilityServiceTest {

//    private val mapleAbilityService = MapleAbilityService(
//        abilityOptionRepository,
//        abilityWeightRepository,
//        abilityNumericRepository,
//        abilityResultRepository,
//        abilityResultEntryRepository
//    )
//
//    private val mapleAbilitySimulationService = MapleAbilitySimulationService(
//        abilityOptionRepository,
//        mapleAbilityService
//    )
//
//    @BeforeEach
//    fun beforeAll() {
//        val abilityOptionMockRepository = AbilityOptionMockRepository()
//        every {
//            abilityOptionRepository.findById(any())
//        } answers {
//            abilityOptionMockRepository.findById(firstArg())
//        }
//
//        val abilityWeightMockRepository = AbilityWeightMockRepository(abilityOptionMockRepository)
//        every {
//            abilityWeightRepository.findAllByLevel(any())
//        } answers {
//            abilityWeightMockRepository.findAllByLevel(firstArg())
//        }
//
//        val abilityNumericMockRepository = AbilityNumericMockRepository(abilityOptionMockRepository)
//        every {
//            abilityNumericRepository.findAllByOptionIdAndLevel(any(), any())
//        } answers {
//            abilityNumericMockRepository.findAllByOptionIdAndLevel(firstArg(), secondArg())
//        }
//
//        every {
//            abilityResultRepository.save(any())
//        } answers {
//            firstArg()
//        }
//
//        every {
//            abilityResultEntryRepository.saveAll(any<List<AbilityResultEntry>>())
//        } answers {
//            listOf(mockk())
//        }
//    }
//
//    @Test
//    fun normalTest() = runBlocking {
//        println("========================================")
//        for (index in 0..100) {
//            val result = mapleAbilityService.getOption(mainLevel = OptionLevel.LEGENDARY, mode = AbilityMode.MIRACLE)
//            AbilityResultDto.of(result).entries.forEach(::println)
//            println("========================================")
//        }
//    }
//
//    @Test
//    fun lockTest() = runBlocking {
//        println("========================================")
//        for (index in 0..100) {
//            val result = mapleAbilityService.getOption(
//                mainLevel = OptionLevel.LEGENDARY,
//                locks = listOf(AbilityOptionDto(9, OptionLevel.UNIQUE, listOf(20)))
//            )
//            AbilityResultDto.of(result).entries.forEach(::println)
//            println("========================================")
//        }
//    }
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
