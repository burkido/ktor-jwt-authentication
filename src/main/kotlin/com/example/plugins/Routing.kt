package com.example.plugins

import com.example.routes.authenticationRoutes
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    routing {
        authenticationRoutes()

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
