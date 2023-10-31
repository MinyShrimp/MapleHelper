package git.shrimp.maple_helper_api.ability.controller

import git.shrimp.maple_helper_api.ability.dto.OptionRequest
import git.shrimp.maple_helper_api.ability.dto.SimulationRequest
import git.shrimp.maple_helper_api.ability.service.data.AbilityInitializeService
import git.shrimp.maple_helper_api.ability.service.simulation.AbilitySimulationService
import git.shrimp.maple_helper_api.ability.service.single.AbilityResultService
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import git.shrimp.maple_helper_core.ability.dto.SimulationOption
import git.shrimp.maple_helper_core.ability.dto.SimulationResult
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
    private val abilityResultService: AbilityResultService,
    private val abilitySimulationService: AbilitySimulationService
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
    suspend fun getOption(
        @RequestBody(required = false) req: OptionRequest?
    ): ResponseEntity<Flux<AbilityResult>> {
        val request = req ?: OptionRequest()
        val results = when (request.stream) {
            true -> this.abilityResultService.getFluxOptions(request)
            false -> Flux.fromIterable(this.abilityResultService.getOptions(request))
        }

        return getStreamResponse(request.stream, results)
    }

    @PostMapping("/simulate")
    suspend fun simulate(
        @RequestBody(required = false) req: SimulationRequest?
    ): ResponseEntity<Flux<SimulationResult>> {
        val request = req ?: SimulationRequest()
        val option = SimulationOption(request.count, request.mainLevel, request.mode)
        val results = when (request.stream) {
            true -> this.abilitySimulationService.getSimulationFluxResult(option, request)
            false -> Flux.fromIterable(this.abilitySimulationService.getSimulationResult(option, request))
        }

        return getStreamResponse(request.stream, results)
    }

    @PostMapping("/initialize")
    fun initialize() {
        this.abilityInitializeService.initialize()
    }
}
