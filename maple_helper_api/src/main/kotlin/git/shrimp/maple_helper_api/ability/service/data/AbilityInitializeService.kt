package git.shrimp.maple_helper_api.ability.service.data

import git.shrimp.maple_helper_api.ability.service.data.initialize.AbilityNumericInitializeService
import git.shrimp.maple_helper_api.ability.service.data.initialize.AbilityOptionInitializeService
import git.shrimp.maple_helper_api.ability.service.data.initialize.AbilityWeightInitializeService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AbilityInitializeService(
    private val abilityOptionInitializeService: AbilityOptionInitializeService,
    private val abilityWeightInitializeService: AbilityWeightInitializeService,
    private val abilityNumericInitializeService: AbilityNumericInitializeService
) {
    @Transactional
    fun initialize() {
        this.abilityOptionInitializeService.initialize()
        this.abilityWeightInitializeService.initialize()
        this.abilityNumericInitializeService.initialize()
    }
}
