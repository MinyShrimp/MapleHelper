package git.shrimp.maple_helper_core.ability.mock

import git.shrimp.maple_helper_core.ability.dto.AbilityOptionData
import git.shrimp.maple_helper_core.ability.mock.repository.AbilityNumericMockRepository
import git.shrimp.maple_helper_core.ability.mock.repository.AbilityOptionMockRepository
import git.shrimp.maple_helper_core.ability.mock.repository.AbilityWeightMockRepository
import git.shrimp.maple_helper_core.ability.types.OptionDataMap
import git.shrimp.maple_helper_core.global.types.OptionLevel

typealias MutableOptionDataMap = MutableMap<OptionLevel, Map<Int, AbilityOptionData>>

class AbilityOptionMockData(
    abilityOptionMockRepository: AbilityOptionMockRepository,
    abilityWeightMockRepository: AbilityWeightMockRepository,
    abilityNumericMockRepository: AbilityNumericMockRepository
) {
    private val dataMap: MutableOptionDataMap = mutableMapOf()

    init {
        val options = abilityOptionMockRepository.findAll()
        for (level in OptionLevel.entries) {
            val optionData = mutableMapOf<Int, AbilityOptionData>()
            for (option in options) {
                val weight = abilityWeightMockRepository.findByOptionIdAndLevel(option.id, level)?.weight ?: continue
                val numeric = abilityNumericMockRepository.findAllByOptionIdAndLevel(option.id, level)?.map { it.numeric } ?: continue

                optionData[option.id] = AbilityOptionData(
                    id = option.id,
                    name = option.name,
                    level = level,
                    weight = weight,
                    numerics = numeric
                )
            }
            this.dataMap[level] = optionData
        }
    }

    fun get(): OptionDataMap {
        return this.dataMap.toMap()
    }
}
