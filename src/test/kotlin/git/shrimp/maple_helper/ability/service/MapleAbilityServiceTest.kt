package git.shrimp.maple_helper.ability.service

import git.shrimp.maple_helper.ability.data.AbilityNumericRepository
import git.shrimp.maple_helper.ability.data.AbilityOptionRepository
import git.shrimp.maple_helper.ability.data.AbilityWeightRepository
import git.shrimp.maple_helper.ability.model.AbilityWeight
import org.junit.jupiter.api.Test

class MapleAbilityServiceTest {
    private val abilityOptionRepository = AbilityOptionRepository()
    private val abilityWeightRepository = AbilityWeightRepository(abilityOptionRepository)
    private val abilityNumericRepository = AbilityNumericRepository(abilityOptionRepository)
    private val mapleAbilityService = MapleAbilityService(abilityWeightRepository, abilityNumericRepository)

    @Test
    fun getOption() {
        println("========================================")
        for (index in 0..100) {
            val options = this.mapleAbilityService.getOption()
            options.forEach(::println)
            println("========================================")
        }
    }
}
