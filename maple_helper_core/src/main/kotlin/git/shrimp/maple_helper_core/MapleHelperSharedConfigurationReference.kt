package git.shrimp.maple_helper_core

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@ComponentScan("git.shrimp.maple_helper_core")
@EnableJpaAuditing
@EnableJpaRepositories("git.shrimp.maple_helper_core.ability.repository")
@EntityScan("git.shrimp.maple_helper_core.ability.model")
class MapleHelperSharedConfigurationReference {}
