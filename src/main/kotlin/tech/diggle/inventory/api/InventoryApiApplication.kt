package tech.diggle.inventory.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class InventoryApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(InventoryApiApplication::class.java, *args)
}
