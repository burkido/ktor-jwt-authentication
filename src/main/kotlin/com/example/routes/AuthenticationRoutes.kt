package com.example.routes

import com.example.repository.AuthenticationRepository
import com.example.routes.authentication.UserParams
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authenticationRoutes(
    authenticationRepository: AuthenticationRepository
) {

    route("/auth") {
        post("/register") {
            val userParams = call.receive<UserParams>()
            val response = authenticationRepository.registerUser(userParams)
            call.respond(response.statusCode, response)
        }
    }
}