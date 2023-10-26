package git.shrimp.maple_helper_api.ability

import git.shrimp.maple_helper_core.ability.service.MapleAbilityService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ability")
class MapleAbilityController(
    private val mapleAbilityService: MapleAbilityService
) {

}
