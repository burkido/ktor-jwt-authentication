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

        get("/test") {
            println("loooo")
            call.respondText("TEST")    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJqd3QtYXVkaWVuY2UiLCJpc3MiOiJodHRwczovL2p3dC1wcm92aWRlci1kb21haW4vIiwiaWQiOjZ9.9tavrAkUnft8CkF7tDlm8OLwTxB0RTST1-Y_ZNXcky4
        }
    }
}
