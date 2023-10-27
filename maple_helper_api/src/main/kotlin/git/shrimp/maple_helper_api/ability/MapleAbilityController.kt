package git.shrimp.maple_helper_api.ability

import git.shrimp.maple_helper_core.ability.dto.AbilityResultDto
import git.shrimp.maple_helper_core.ability.service.AbilityInitializeService
import git.shrimp.maple_helper_core.ability.service.MapleAbilityService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.mono
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/ability")
class MapleAbilityController(
    private val abilityInitializeService: AbilityInitializeService,
    private val mapleAbilityService: MapleAbilityService
) {
    @GetMapping
    fun getOption(
        @RequestParam("count", defaultValue = "1") count: Int,
        @RequestParam("stream", defaultValue = "false") stream: Boolean,
    ): ResponseEntity<Flux<AbilityResultDto>> {
        val result = Flux.range(0, count).flatMap {
            mono(Dispatchers.Default) {
                AbilityResultDto.of(mapleAbilityService.getOption())
            }
        }

        return if (stream) {
            ResponseEntity.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(result)
        } else {
            ResponseEntity.ok(result)
        }
    }

    @PostMapping("/initialize")
    fun initialize() {
        this.abilityInitializeService.initialize()
    }
}
