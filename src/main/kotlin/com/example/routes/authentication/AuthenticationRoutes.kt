package com.example.routes.authentication

import com.example.repository.AuthenticationRepository
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.authenticationRoutes() {

    val authenticationRepository: AuthenticationRepository by inject()

    route("/auth") {

        post("/register") {
            val userParams = call.receive<RegisterUserParams>()
            val response = authenticationRepository.registerUser(userParams)
            call.respond(response.statusCode, response)
        }

        post("/login") {
            val userParams = call.receive<LoginUserParams>()
            val response = authenticationRepository.loginUser(userParams)
            call.respond(response.statusCode, response)
        }

        authenticate {
            get("/authenticate") {
                val principal = call.principal<JWTPrincipal>()
                val userId = principal?.getClaim("id", String::class)
                call.respond(HttpStatusCode.OK, "Your userId is $userId")
            }
        }
    }
}