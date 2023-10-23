package git.shrimp.maple_helper.api.ability.controller

import git.shrimp.maple_helper.core.ability.dto.SimulationOption
import git.shrimp.maple_helper.core.ability.dto.SimulationResult
import git.shrimp.maple_helper.core.ability.dto.TargetDto
import git.shrimp.maple_helper.core.ability.model.AbilityMode
import git.shrimp.maple_helper.core.ability.service.MapleAbilityService
import git.shrimp.maple_helper.core.global.model.OptionLevel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ability")
class MapleAbilityController(
    private val mapleAbilityService: MapleAbilityService
) {
    @GetMapping("/simulation")
    suspend fun getAbilityOptions(): List<SimulationResult> {
        val targetDto = TargetDto(8, OptionLevel.UNIQUE, arrayOf(20))
        val option = SimulationOption(10000, OptionLevel.LEGENDARY, AbilityMode.MIRACLE)

        return mapleAbilityService.simulation(targetDto, option)
    }
}
