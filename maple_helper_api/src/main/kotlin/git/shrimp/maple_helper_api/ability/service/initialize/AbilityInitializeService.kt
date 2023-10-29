package git.shrimp.maple_helper_api.ability.service.initialize

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
