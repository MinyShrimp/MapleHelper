package git.shrimp.maple_helper.discordbot.global.listener

import discord4j.core.event.domain.Event
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import reactor.core.publisher.Mono

interface EventListener<T : Event> {
    private val log: KLogger
        get() = KotlinLogging.logger {}

    fun getEventType(): Class<T>
    fun execute(event: T): Mono<Void>

    fun handleError(
        error: Throwable
    ): Mono<Void> {
        this.log.error(error) {
            "Unable to process ${this.getEventType().simpleName}"
        }

        return Mono.empty()
    }
}
