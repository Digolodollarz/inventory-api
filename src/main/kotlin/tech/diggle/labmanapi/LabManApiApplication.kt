package tech.diggle.labmanapi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class LabManApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(LabManApiApplication::class.java, *args)
}
