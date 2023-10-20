package git.shrimp.maple_helper.ability.service

import git.shrimp.maple_helper.ability.data.AbilityOptionRepository
import git.shrimp.maple_helper.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.ability.model.AbilityWeight
import org.junit.jupiter.api.Test

class MapleAbilityServiceTest {
    private val abilityOptionRepository = AbilityOptionRepository()
    private val abilityWeightRepository = AbilityWeightRepository(abilityOptionRepository)
    private val mapleAbilityService = MapleAbilityService(abilityWeightRepository)

    @Test
    fun getItems() {
        val rareItems = this.abilityWeightRepository.getItems(AbilityWeight.OptionLevel.RARE)
        val totalRareWeight = rareItems.sumOf { it.weight }
        println("total rare weight: $totalRareWeight")

        val epicItems = this.abilityWeightRepository.getItems(AbilityWeight.OptionLevel.EPIC)
        val totalEpicWeight = epicItems.sumOf { it.weight }
        println("total epic weight: $totalEpicWeight")

        val uniqueItems = this.abilityWeightRepository.getItems(AbilityWeight.OptionLevel.UNIQUE)
        val totalUniqueWeight = uniqueItems.sumOf { it.weight }
        println("total unique weight: $totalUniqueWeight")

        val legendaryItems = this.abilityWeightRepository.getItems(AbilityWeight.OptionLevel.LEGENDARY)
        val totalLegendaryWeight = legendaryItems.sumOf { it.weight }
        println("total legendary weight: $totalLegendaryWeight")
    }

    @Test
    fun getOption() {
        val options = this.mapleAbilityService.getOption()
        options.forEach(::println)
    }
}
