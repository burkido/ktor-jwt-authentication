package com.example

import com.example.database.DatabaseFactory
import com.example.plugins.*
import io.ktor.application.*


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

//jwtauth!!
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    //configureKoin()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
