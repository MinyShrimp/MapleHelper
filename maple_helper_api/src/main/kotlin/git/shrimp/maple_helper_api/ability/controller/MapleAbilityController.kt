package git.shrimp.maple_helper_api.ability.controller

import git.shrimp.maple_helper_api.ability.service.initialize.AbilityInitializeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ability")
class MapleAbilityController(
    private val abilityInitializeService: AbilityInitializeService,
//    private val mapleAbilityService: MapleAbilityService,
//    private val mapleAbilitySimulationService: MapleAbilitySimulationService
) {
//    @GetMapping
//    fun getOption(
//        @RequestParam("count", defaultValue = "1") count: Int,
//        @RequestParam("stream", defaultValue = "false") stream: Boolean,
//    ): ResponseEntity<Flux<AbilityResultDto>> {
//        val result = Flux.range(0, count).flatMap {
//            mono(Dispatchers.Default) {
//                AbilityResultDto.of(mapleAbilityService.getOption())
//            }
//        }
//
//        return if (stream) {
//            ResponseEntity.ok()
//                .contentType(MediaType.TEXT_EVENT_STREAM)
//                .body(result)
//        } else {
//            ResponseEntity.ok(result)
//        }
//    }
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
