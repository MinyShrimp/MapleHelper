package git.shrimp.maple_helper.discordbot.global.listener

import discord4j.core.`object`.entity.Message
import reactor.core.publisher.Mono

class CommandUtil {
    companion object {
        fun commandBuild(
            eventMessage: Message,
            prompt: String,
            completion: String
        ): Mono<Void> {
            return Mono.just(eventMessage)
                .filter { message -> message.author.map { user -> !user.isBot }.orElse(false) }
                .filter { message -> message.content == "!${prompt}" }
                .flatMap { it.channel }
                .flatMap { channel -> channel.createMessage(completion) }
                .then()
        }
    }
}
