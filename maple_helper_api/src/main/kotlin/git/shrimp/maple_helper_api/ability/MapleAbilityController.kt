package git.shrimp.maple_helper_api.ability

import git.shrimp.maple_helper_core.ability.dto.AbilityResultDto
import git.shrimp.maple_helper_core.ability.service.AbilityInitializeService
import git.shrimp.maple_helper_core.ability.service.MapleAbilityService
import org.springframework.web.bind.annotation.*
import java.util.stream.IntStream

@RestController
@RequestMapping("/api/ability")
class MapleAbilityController(
    private val abilityInitializeService: AbilityInitializeService,
    private val mapleAbilityService: MapleAbilityService
) {
    @GetMapping
    fun getOption(
        @RequestParam("count", defaultValue = "1") count: String
    ): List<AbilityResultDto> {
        return IntStream.range(0, count.toInt()).mapToObj {
            val option = this.mapleAbilityService.getOption()
            AbilityResultDto.of(option)
        }.toList()
    }

    @PostMapping("/initialize")
    fun initialize() {
        this.abilityInitializeService.initialize()
    }
}
