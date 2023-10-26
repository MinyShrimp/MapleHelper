package git.shrimp.maple_helper_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
class MapleHelperApiApplication

fun main(args: Array<String>) {
    runApplication<MapleHelperApiApplication>(*args)
}
