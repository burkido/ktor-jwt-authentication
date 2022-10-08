package com.example

import com.example.database.DatabaseFactory
import io.ktor.application.*
import com.example.plugins.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

//jwtauth!!
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
