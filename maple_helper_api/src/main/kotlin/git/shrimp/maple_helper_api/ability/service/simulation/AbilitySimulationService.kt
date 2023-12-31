package git.shrimp.maple_helper_api.ability.service.simulation

import git.shrimp.maple_helper_api.ability.dto.SimulationRequest
import git.shrimp.maple_helper_api.ability.service.data.AbilityOptionCachingService
import git.shrimp.maple_helper_core.ability.dto.SimulationOption
import git.shrimp.maple_helper_core.ability.dto.SimulationResult
import git.shrimp.maple_helper_core.ability.service.MapleAbilitySimulationService
import git.shrimp.maple_helper_core.ability.service.MapleAbilitySimulationService.Companion.MAX_COUNT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class AbilitySimulationService(
    abilityOptionCachingService: AbilityOptionCachingService,
    private val abilitySimulationSaveService: AbilitySimulationSaveService,
    private val mapleAbilitySimulationService: MapleAbilitySimulationService
) {
    private val dataMap = abilityOptionCachingService.getCachedOptionData()

    suspend fun getSimulationResult(
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

    suspend fun getSimulationFluxResult(
        option: SimulationOption,
        request: SimulationRequest,
    ): Flux<SimulationResult> {
        return Flux.range(0, option.count).flatMap {
            mono(Dispatchers.Default) {
                when (
                    val result = mapleAbilitySimulationService.simulateOne(dataMap, option.copy(count = 1), request.targets, request.locks)
                ) {
                    null -> SimulationResult(MAX_COUNT, option.mode, emptyList())
                    else -> withContext(Dispatchers.IO) {
                        val entity = abilitySimulationSaveService.saveResult(result, request)
                        result.copy(id = entity.id)
                    }
                }
            }
        }
    }
}
