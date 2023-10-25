package git.shrimp.maple_helper_discord_bot.ability

import io.github.oshai.kotlinlogging.KotlinLogging
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter


class MapleAbilityListener : ListenerAdapter() {
    private val logger = KotlinLogging.logger {}

    override fun onMessageReceived(
        event: MessageReceivedEvent
    ) {
        val message = event.message
        this.logger.info { "Received message: ${message.contentRaw}" }
        if (message.author.isBot) return

        if (message.contentRaw.startsWith("!echo")) {
            val msg = message.contentRaw.replace("!echo ", "")
            event.channel.sendMessage(msg).queue()
        }
    }
}
