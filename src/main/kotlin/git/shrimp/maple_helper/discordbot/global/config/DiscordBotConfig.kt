package git.shrimp.maple_helper.discordbot.global.config

import discord4j.core.DiscordClientBuilder
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.Event
import git.shrimp.maple_helper.discordbot.global.listener.EventListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiscordBotConfig {
    @Bean
    fun <T : Event> gatewayDiscordClient(
        @Value("\${discord.bot.token}") token: String,
        eventListeners: List<EventListener<T>>
    ): GatewayDiscordClient {
        val client = DiscordClientBuilder.create(token).build().login().block()
            ?: throw RuntimeException("DiscordClient is null")

        eventListeners.forEach { eventListener ->
            client.on(eventListener.getEventType())
                .flatMap(eventListener::execute)
                .onErrorResume(eventListener::handleError)
                .subscribe()
        }

        return client
    }
}
