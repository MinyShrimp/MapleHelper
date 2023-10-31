package git.shrimp.maple_helper_api.ability.service.data

import git.shrimp.maple_helper_api.ability.entity.data.AbilityOptionEntity
import git.shrimp.maple_helper_api.ability.repository.data.AbilityOptionRepository
import git.shrimp.maple_helper_core.ability.dto.AbilityOptionData
import git.shrimp.maple_helper_core.ability.types.OptionDataMap
import git.shrimp.maple_helper_core.global.types.OptionLevel
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class AbilityOptionCachingService(
    private val abilityOptionRepository: AbilityOptionRepository
) {
    private lateinit var optionDataCache: OptionDataMap

    @PostConstruct
    fun init() {
        this.optionDataCache = this.getAllOptionData()
    }

    private fun getOptionData(
        option: AbilityOptionEntity,
        optionLevel: OptionLevel
    ): AbilityOptionData? {
        val weight = option.weights.find { it.level == optionLevel }?.weight ?: return null
        val numerics = option.numerics.filter { it.level == optionLevel }

        return AbilityOptionData(
            id = option.id,
            name = option.name,
            level = optionLevel,
            weight = weight,
            numerics = numerics.map { it.numerics }.sortedBy { it[0] },
        )
    }

    private fun getAllOptionData(): OptionDataMap {
        val options = this.abilityOptionRepository.findAllOrderById()
        val optionDataMap = mutableMapOf<OptionLevel, Map<Int, AbilityOptionData>>()

        for (level in OptionLevel.entries) {
            val optionData = mutableMapOf<Int, AbilityOptionData>()
            for (option in options) {
                optionData[option.id] = this.getOptionData(option, level) ?: continue
            }
            optionDataMap[level] = optionData
        }

        return optionDataMap
    }

    fun getCachedOptionData(): OptionDataMap {
        return optionDataCache
    }
}
