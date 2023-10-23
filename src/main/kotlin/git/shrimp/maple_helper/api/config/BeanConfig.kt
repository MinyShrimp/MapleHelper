package git.shrimp.maple_helper.api.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {
    @Bean
    fun kJson(): Json {
        return Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }
}
