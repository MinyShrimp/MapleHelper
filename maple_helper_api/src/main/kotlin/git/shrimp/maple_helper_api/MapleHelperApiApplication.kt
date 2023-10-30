package git.shrimp.maple_helper_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
@EntityScan("git.shrimp.maple_helper_api.**.entity")
@EnableJpaAuditing
@EnableJpaRepositories("git.shrimp.maple_helper_api.**.repository")
class MapleHelperApiApplication

fun main(args: Array<String>) {
    runApplication<MapleHelperApiApplication>(*args)
}
