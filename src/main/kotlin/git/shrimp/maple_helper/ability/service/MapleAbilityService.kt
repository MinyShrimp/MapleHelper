package git.shrimp.maple_helper.ability.service

import git.shrimp.maple_helper.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.ability.model.AbilityOption
import git.shrimp.maple_helper.ability.model.AbilityWeight
import git.shrimp.maple_helper.global.model.OptionLevel
import org.springframework.stereotype.Service

@Service
class MapleAbilityService(
    private val abilityWeightRepository: AbilityWeightRepository
) {
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
        val legendaryOptions = this.abilityWeightRepository.getItems(OptionLevel.LEGENDARY)
        val totalLegendaryWeight = legendaryOptions.sumOf { it.weight }

        val random = this.getRandom(totalLegendaryWeight)
        return this.getOption(legendaryOptions, random)
    }

    private fun getUniqueOption(
        withoutOptions: List<AbilityOption>
    ): AbilityOption {
        val uniqueOptions = this.abilityWeightRepository.getItems(OptionLevel.UNIQUE, withoutOptions)
        val totalUniqueWeight = uniqueOptions.sumOf { it.weight }

        val random = this.getRandom(totalUniqueWeight)
        return this.getOption(uniqueOptions, random)
    }

    private fun getEpicOption(
        withoutOptions: List<AbilityOption>
    ): AbilityOption {
        val epicOptions = this.abilityWeightRepository.getItems(OptionLevel.EPIC, withoutOptions)
        val totalEpicWeight = epicOptions.sumOf { it.weight }

        val random = this.getRandom(totalEpicWeight)
        return this.getOption(epicOptions, random)
    }

    private fun getMainOption(): AbilityOption {
        return this.getLegendaryOption()
    }

    private fun getSubOption(
        options: List<AbilityOption>
    ): AbilityOption {
        val random = this.getRandom(100)
        return if(random < 15) { this.getUniqueOption(options) } else { this.getEpicOption(options) }
    }

    fun getOption(): List<AbilityOption> {
        val firstOption = this.getMainOption()
        val secondOption = this.getSubOption(listOf(firstOption))
        val thirdOption = this.getSubOption(listOf(firstOption, secondOption))

        return listOf(firstOption, secondOption, thirdOption)
    }
}
