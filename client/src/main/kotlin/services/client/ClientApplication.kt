package services.client

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class ClientApplication

fun main(args: Array<String>) {
    SpringApplication.run(ClientApplication::class.java, *args)
}