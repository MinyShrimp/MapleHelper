package git.shrimp.maple_helper_api.ability.controller

import git.shrimp.maple_helper_api.ability.service.AbilityOptionCachingService
import git.shrimp.maple_helper_api.ability.service.AbilityResultService
import git.shrimp.maple_helper_api.ability.service.initialize.AbilityInitializeService
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import git.shrimp.maple_helper_core.ability.service.MapleAbilityService
import git.shrimp.maple_helper_core.ability.types.AbilityMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.withContext
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/ability")
class MapleAbilityController(
    private val abilityInitializeService: AbilityInitializeService,
    private val abilityOptionCachingService: AbilityOptionCachingService,
    private val mapleAbilityService: MapleAbilityService,
    private val abilityResultService: AbilityResultService
//    private val mapleAbilitySimulationService: MapleAbilitySimulationService
) {
    @GetMapping
    fun getOption(
        @RequestParam("count", defaultValue = "1") count: Int,
        @RequestParam("stream", defaultValue = "false") stream: Boolean,
        @RequestParam("mode", defaultValue = "NORMAL") mode: AbilityMode,
    ): ResponseEntity<Flux<AbilityResult>> {
        val results = Flux.range(0, count).flatMap {
            mono(Dispatchers.Default) {
                val dataMap = abilityOptionCachingService.getCachedOptionData()
                val result = mapleAbilityService.getOption(
                    dataMap = dataMap,
                    mode = mode
                )

                withContext(Dispatchers.IO) {
                    val entity = abilityResultService.saveResult(result)
                    result.copy(id = entity.id)
                }
            }
        }

        return if (stream) {
            ResponseEntity.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(results)
        } else {
            ResponseEntity.ok(results)
        }
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
