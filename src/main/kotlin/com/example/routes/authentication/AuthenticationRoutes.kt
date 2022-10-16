package com.example.routes.authentication

import com.example.repository.AuthenticationRepository
import com.example.routes.authentication.UserParams
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authenticationRoutes(
    authenticationRepository: AuthenticationRepository
) {

    route("/auth") {
        authenticate {
            get("/secret") {
                val principal = call.principal<JWTPrincipal>()
                val userId = principal?.getClaim("id", String::class)
                call.respond(HttpStatusCode.OK, "Your userId is $userId")
            }
        }

        post("/register") {
            val userParams = call.receive<UserParams>()
            val response = authenticationRepository.registerUser(userParams)
            call.respond(response.statusCode, response)
        }

        get("/rr") {
            call.respond(HttpStatusCode.OK, "rr")
        }
    }
}