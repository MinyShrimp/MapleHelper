package git.shrimp.maple_helper.ability.service

import git.shrimp.maple_helper.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.ability.model.AbilityOption
import git.shrimp.maple_helper.ability.model.AbilityWeight
import org.springframework.stereotype.Service

@Service
class MapleAbilityService(
    private val abilityWeightRepository: AbilityWeightRepository
) {
    private val legendaryOptions = this.abilityWeightRepository.getItems(AbilityWeight.OptionLevel.LEGENDARY)
    private val totalLegendaryWeight = this.abilityWeightRepository.getTotalWeight(AbilityWeight.OptionLevel.LEGENDARY)

    private val uniqueOptions = this.abilityWeightRepository.getItems(AbilityWeight.OptionLevel.UNIQUE)
    private val totalUniqueWeight = this.abilityWeightRepository.getTotalWeight(AbilityWeight.OptionLevel.UNIQUE)

    private val epicOptions = this.abilityWeightRepository.getItems(AbilityWeight.OptionLevel.EPIC)
    private val totalEpicWeight = this.abilityWeightRepository.getTotalWeight(AbilityWeight.OptionLevel.EPIC)


    private fun getRandom(
        maxNumber: Int
    ): Int {
        return (0..maxNumber).random()
    }

    private fun getOption(
        weights: List<AbilityWeight>,
        random: Int
    ): AbilityOption {
        var sum = 0
        for (item in weights) {
            sum += item.weight
            if (sum >= random) {
                return item.option
            }
        }
        throw Exception("getFirstOption() failed")
    }

    private fun getLegendaryOption(): AbilityOption {
        val random = this.getRandom(this.totalLegendaryWeight)
        return this.getOption(this.legendaryOptions, random)
    }

    private fun getUniqueOption(): AbilityOption {
        val random = this.getRandom(this.totalUniqueWeight)
        return this.getOption(this.uniqueOptions, random)
    }

    private fun getEpicOption(): AbilityOption {
        val random = this.getRandom(this.totalEpicWeight)
        return this.getOption(this.epicOptions, random)
    }

    fun getOption(): List<AbilityOption> {
        val firstOption = this.getLegendaryOption()

        val random = this.getRandom(100)
        val secondOption = if(random < 15) { this.getUniqueOption() } else { this.getEpicOption() }

        val random2 = this.getRandom(100)
        val thirdOption = if(random2 < 15) { this.getUniqueOption() } else { this.getEpicOption() }

        return listOf(firstOption, secondOption, thirdOption)
    }
}
