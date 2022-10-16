package com.example.plugins


import com.example.repository.AuthenticationRepositoryImpl
import com.example.routes.authentication.authenticationRoutes
import com.example.service.authentication.AuthenticationServiceImpl
import io.ktor.routing.*
import io.ktor.application.*

fun Application.configureRouting() {

    //val authenticationRepository = AuthenticationRepositoryImpl(AuthenticationServiceImpl())

    routing {

        authenticationRoutes()
    }
}
