package git.shrimp.maple_helper.discordbot.global.config

import git.shrimp.maple_helper.discordbot.ability.MapleAbilityListener
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiscordBotConfig {
    @Bean
    fun gatewayDiscordClient(
        @Value("\${discord.bot.token}") token: String,
    ): JDA {
        return JDABuilder.createDefault(token)
            .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
            .setBulkDeleteSplittingEnabled(false)
            .setMemberCachePolicy(MemberCachePolicy.VOICE.or(MemberCachePolicy.OWNER))
            .setChunkingFilter(ChunkingFilter.NONE)
            .disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING)
            .setLargeThreshold(50)
            .setAutoReconnect(true)
            .setStatus(OnlineStatus.ONLINE)
            .addEventListeners(MapleAbilityListener())
            .enableIntents(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MESSAGE_TYPING
            ).build()
    }
}
