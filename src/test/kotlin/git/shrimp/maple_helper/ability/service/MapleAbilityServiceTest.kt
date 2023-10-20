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
    fun getOption() {
        val options = this.mapleAbilityService.getOption()
        options.forEach(::println)
    }
}
