package git.shrimp.maple_helper_api.ability.service.single

import git.shrimp.maple_helper_api.ability.dto.OptionRequest
import git.shrimp.maple_helper_api.ability.service.data.AbilityOptionCachingService
import git.shrimp.maple_helper_core.ability.dto.AbilityResult
import git.shrimp.maple_helper_core.ability.service.MapleAbilityService
import kotlinx.coroutines.*
import kotlinx.coroutines.reactor.mono
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class AbilityResultService(
    abilityOptionCachingService: AbilityOptionCachingService,
    private val abilityResultSaveService: AbilityResultSaveService,
    private val mapleAbilityService: MapleAbilityService,
) {
    private val dataMap = abilityOptionCachingService.getCachedOptionData()

    suspend fun getOptions(
        request: OptionRequest,
    ): List<AbilityResult> {
        val results = coroutineScope {
            List(request.count) {
                async(Dispatchers.Default) {
                    mapleAbilityService.getOption(dataMap, request.mainLevel, request.mode, request.locks)
                }
            }.awaitAll()
        }

        val entities = withContext(Dispatchers.IO) {
            abilityResultSaveService.saveResultBulk(results, request)
        }

        return results.zip(entities).map { (result, entity) ->
            result.copy(id = entity.id)
        }
    }

    suspend fun getFluxOptions(
        request: OptionRequest,
    ): Flux<AbilityResult> {
        return Flux.range(0, request.count).flatMap {
            mono(Dispatchers.Default) {
                val option = mapleAbilityService.getOption(dataMap, request.mainLevel, request.mode, request.locks)
                withContext(Dispatchers.IO) {
                    val entity = abilityResultSaveService.saveResult(option, request)
                    option.copy(id = entity.id)
                }
            }
        }
    }
}
