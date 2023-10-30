package git.shrimp.maple_helper_api.ability.controller

import git.shrimp.maple_helper_api.ability.dto.OptionRequest
import git.shrimp.maple_helper_api.ability.dto.SimulationRequest
import git.shrimp.maple_helper_api.ability.service.AbilityOptionCachingService
import git.shrimp.maple_helper_api.ability.service.AbilityResultService
import git.shrimp.maple_helper_api.ability.service.AbilitySimulationService
import git.shrimp.maple_helper_api.ability.service.initialize.AbilityInitializeService
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import git.shrimp.maple_helper_core.ability.dto.SimulationOption
import git.shrimp.maple_helper_core.ability.dto.SimulationResult
import git.shrimp.maple_helper_core.ability.service.MapleAbilityService
import git.shrimp.maple_helper_core.ability.service.MapleAbilitySimulationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.withContext
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/ability")
class MapleAbilityController(
    private val abilityInitializeService: AbilityInitializeService,
    private val abilityOptionCachingService: AbilityOptionCachingService,
    private val abilityResultService: AbilityResultService,
    private val abilitySimulationService: AbilitySimulationService,
    private val mapleAbilityService: MapleAbilityService,
    private val mapleAbilitySimulationService: MapleAbilitySimulationService
) {
    private fun <T> getStreamResponse(
        isStream: Boolean, data: T
    ): ResponseEntity<T> {
        return if (isStream) {
            ResponseEntity.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(data)
        } else {
            ResponseEntity.ok(data)
        }
    }

    @PostMapping
    fun getOption(
        @RequestBody(required = false) req: OptionRequest?
    ): ResponseEntity<Flux<AbilityResult>> {
        val request = req ?: OptionRequest()
        val dataMap = abilityOptionCachingService.getCachedOptionData()

        val results = Flux.range(0, request.count).flatMap {
            mono(Dispatchers.Default) {
                val result = mapleAbilityService.getOption(
                    dataMap = dataMap,
                    mode = request.mode,
                    mainLevel = request.mainLevel,
                    locks = request.locks,
                )

                withContext(Dispatchers.IO) {
                    val entity = abilityResultService.saveResult(result, request)
                    result.copy(id = entity.id)
                }
            }
        }

        return getStreamResponse(request.stream, results)
    }

    @PostMapping("/simulate")
    fun simulate(
        @RequestBody(required = false) req: SimulationRequest?
    ): ResponseEntity<Flux<SimulationResult>> {
        val request = req ?: SimulationRequest()
        val dataMap = abilityOptionCachingService.getCachedOptionData()

        val option = SimulationOption(
            mainLevel = request.mainLevel,
            mode = request.mode,
            maxCount = request.count
        )

        val results = mono {
            val simulationResults = mapleAbilitySimulationService.simulation(dataMap, option, request.targets, request.locks)

            simulationResults.map {
                withContext(Dispatchers.IO) {
                    val entity = abilitySimulationService.saveResult(it, request)
                    it.copy(id = entity.id)
                }
            }
        }.flatMapMany { results -> Flux.fromIterable(results) }

        return getStreamResponse(request.stream, results)
    }

    @PostMapping("/initialize")
    fun initialize() {
        this.abilityInitializeService.initialize()
    }
}
