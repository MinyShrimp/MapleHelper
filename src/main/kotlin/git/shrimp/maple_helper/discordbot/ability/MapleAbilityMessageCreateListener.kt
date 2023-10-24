package git.shrimp.maple_helper.discordbot.ability

import discord4j.core.event.domain.message.MessageCreateEvent
import git.shrimp.maple_helper.core.ability.service.AbilityNumericInitializeService
import git.shrimp.maple_helper.discordbot.global.listener.CommandUtil
import git.shrimp.maple_helper.discordbot.global.listener.EventListener
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class MapleAbilityMessageCreateListener(
    private val abilityNumericInitializeService: AbilityNumericInitializeService
) : EventListener<MessageCreateEvent> {
    override fun getEventType(): Class<MessageCreateEvent> {
        return MessageCreateEvent::class.java
    }

    override fun execute(
        event: MessageCreateEvent
    ): Mono<Void> {
        return CommandUtil.commandBuild(event.message, "init", "init")
    }
}
