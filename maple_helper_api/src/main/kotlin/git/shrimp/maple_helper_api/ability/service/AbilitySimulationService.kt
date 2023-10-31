package git.shrimp.maple_helper_api.ability.service

import git.shrimp.maple_helper_api.ability.dto.SimulationRequest
import git.shrimp.maple_helper_core.ability.dto.SimulationOption
import git.shrimp.maple_helper_core.ability.dto.SimulationResult
import git.shrimp.maple_helper_core.ability.service.MapleAbilitySimulationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class AbilitySimulationService(
    abilityOptionCachingService: AbilityOptionCachingService,
    private val abilitySimulationSaveService: AbilitySimulationSaveService,
    private val mapleAbilitySimulationService: MapleAbilitySimulationService
) {
    private val dataMap = abilityOptionCachingService.getCachedOptionData()

    suspend fun simulation(
        option: SimulationOption,
        request: SimulationRequest,
    ): List<SimulationResult> {
        val simulations = mapleAbilitySimulationService.simulate(dataMap, option, request.targets, request.locks)
        val entities = withContext(Dispatchers.IO) {
            abilitySimulationSaveService.saveResultBulk(simulations, request)
        }

        return simulations.zip(entities).map { (result, entity) ->
            result.copy(id = entity.id)
        }
    }
}
