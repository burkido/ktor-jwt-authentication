package com.example.plugins

import com.example.model.ApiResponse
import com.example.repository.AuthenticationRepository
import com.example.repository.AuthenticationRepositoryImpl
import com.example.routes.authentication.UserParams
import com.example.routes.authenticationRoutes
import com.example.service.authentication.AuthenticationService
import com.example.service.authentication.AuthenticationServiceImpl
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    val authenticationRepository = AuthenticationRepositoryImpl(AuthenticationServiceImpl())

    routing {

        authenticationRoutes(authenticationRepository)

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
