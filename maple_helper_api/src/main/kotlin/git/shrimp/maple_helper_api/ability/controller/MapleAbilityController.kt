package git.shrimp.maple_helper_api.ability.controller

import git.shrimp.maple_helper_api.ability.dto.OptionRequest
import git.shrimp.maple_helper_api.ability.service.AbilityOptionCachingService
import git.shrimp.maple_helper_api.ability.service.AbilityResultService
import git.shrimp.maple_helper_api.ability.service.initialize.AbilityInitializeService
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import git.shrimp.maple_helper_core.ability.service.MapleAbilityService
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
    private val mapleAbilityService: MapleAbilityService
//    private val mapleAbilitySimulationService: MapleAbilitySimulationService
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

        val results = Flux.range(0, request.count).flatMap {
            mono(Dispatchers.Default) {
                val dataMap = abilityOptionCachingService.getCachedOptionData()
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
//
//    @GetMapping("/simulate")
//    fun simulate(
//        @RequestParam("count", defaultValue = "1") count: Int
//    ): Flux<SimulationResultDto> {
//        val option = SimulationOption(
//            mainLevel = OptionLevel.LEGENDARY,
//            mode = AbilityMode.MIRACLE,
//            maxCount = count
//        )
//        val targets = listOf(
//            AbilityOptionDto(optionId = 9, level = OptionLevel.LEGENDARY, numeric = listOf(20))
//        )
//
//        return mono { mapleAbilitySimulationService.simulation(option, targets) }
//            .flatMapMany { results -> Flux.fromIterable(results) }
//    }

    @PostMapping("/initialize")
    fun initialize() {
        this.abilityInitializeService.initialize()
    }
}
