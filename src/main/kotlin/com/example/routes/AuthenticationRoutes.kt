package com.example.routes

import com.example.repository.AuthenticationRepository
import com.example.routes.authentication.UserParams
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authenticationRoutes(
    authenticationRepository: AuthenticationRepository
) {


    route("/auth") {

        authenticate {
            get("/login") {
                println("Inside login")
                call.respond("Working fine but it shouldn't be")
            }
        }

        post("/register") {
            val userParams = call.receive<UserParams>()
            val response = authenticationRepository.registerUser(userParams)
            call.respond(response.statusCode, response)
        }
    }
}