package community.whatever.onembackendkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OnemBackendKotlinApplication

fun main(args: Array<String>) {
    runApplication<OnemBackendKotlinApplication>(*args)
}
