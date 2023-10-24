package git.shrimp.maple_helper.discordbot.ability

import discord4j.core.event.domain.message.MessageCreateEvent
import git.shrimp.maple_helper.core.ability.model.AbilityMode
import git.shrimp.maple_helper.core.ability.service.MapleAbilityService
import git.shrimp.maple_helper.core.global.model.OptionLevel
import git.shrimp.maple_helper.discordbot.global.listener.CommandUtil
import git.shrimp.maple_helper.discordbot.global.listener.EventListener
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class MapleAbilityMessageCreateListener(
    private val mapleAbilityService: MapleAbilityService
) : EventListener<MessageCreateEvent> {
    override fun getEventType(): Class<MessageCreateEvent> {
        return MessageCreateEvent::class.java
    }

    fun run(): String {
        val results = this.mapleAbilityService.getOption(OptionLevel.LEGENDARY, AbilityMode.MIRACLE)

        results.forEach(::println)
        return results.joinToString("\n") { it.toString() }
    }

    override fun execute(
        event: MessageCreateEvent
    ): Mono<Void> {
        return CommandUtil.commandBuild(event.message, "init", this.run())
    }
}
