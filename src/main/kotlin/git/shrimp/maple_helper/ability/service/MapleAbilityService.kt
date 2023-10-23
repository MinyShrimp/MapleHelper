package git.shrimp.maple_helper.ability.service

import git.shrimp.maple_helper.ability.data.AbilityNumericRepository
import git.shrimp.maple_helper.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.ability.dto.AbilityResult
import git.shrimp.maple_helper.ability.model.AbilityNumeric
import git.shrimp.maple_helper.ability.model.AbilityOption
import git.shrimp.maple_helper.ability.model.AbilityWeight
import git.shrimp.maple_helper.global.model.OptionLevel
import org.springframework.stereotype.Service

@Service
class MapleAbilityService(
    private val abilityWeightRepository: AbilityWeightRepository,
    private val abilityNumericRepository: AbilityNumericRepository
) {
    private fun getRandom(
        maxNumber: Int
    ): Int {
        return (0..maxNumber).random()
    }

    private fun getRandomWeight(
        weights: List<AbilityWeight>
    ): AbilityWeight? {
        val totalWeight = weights.sumOf { it.weight }
        val random = this.getRandom(totalWeight)

        var sum = 0
        for (item in weights) {
            sum += item.weight
            if (sum >= random) { return item }
        }
        return null
    }

    private fun getOption(
        weights: List<AbilityWeight>
    ): AbilityOption {
        return this.getRandomWeight(weights)?.option ?: throw Exception("getOption() failed")
    }

    private fun getNumeric(
        numerics: List<AbilityNumeric>
    ): Array<Int> {
        return (this.getRandomWeight(numerics) as AbilityNumeric?)?.numeric ?: throw Exception("getNumeric() failed ")
    }

    private fun getResult(
        level: OptionLevel,
        withoutOptions: List<AbilityOption> = listOf()
    ): AbilityResult {
        val weights = this.abilityWeightRepository.getItems(level, withoutOptions)
        val option = this.getOption(weights)

        val numerics = this.abilityNumericRepository.getItems(option.id, level)
        val numeric = this.getNumeric(numerics)

        return AbilityResult(
            id = option.id,
            name = option.name,
            level = level,
            numeric = numeric,
        )
    }

    private fun getMainOption(): AbilityResult {
        return this.getResult(OptionLevel.LEGENDARY)
    }

    private fun getSubOption(
        options: List<AbilityOption>
    ): AbilityResult {
        val random = this.getRandom(100)
        return if(random < 15) { this.getResult(OptionLevel.UNIQUE, options) } else { this.getResult(OptionLevel.EPIC, options) }
    }

    fun getOption(): List<AbilityResult> {
        val firstOption = this.getMainOption()
        val secondOption = this.getSubOption(listOf(firstOption))
        val thirdOption = this.getSubOption(listOf(firstOption, secondOption))

        return listOf(firstOption, secondOption, thirdOption)
    }
}
