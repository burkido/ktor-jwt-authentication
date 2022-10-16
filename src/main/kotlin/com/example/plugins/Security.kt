package com.example.plugins

import io.ktor.auth.*
import io.ktor.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.security.JwtConfig
import io.ktor.application.*

fun Application.configureSecurity() {

    JwtConfig.initialize("jwt-auth")
    authentication {
        jwt {
            val jwtAudience = environment.config.property("jwt.audience").getString()
            realm = environment.config.property("jwt.realm").getString()
            verifier(
                JWT
                    .require(Algorithm.HMAC256("jwt-auth"))
                    .withAudience(jwtAudience)
                    .withIssuer(environment.config.property("jwt.domain").getString())
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
//            challenge { defaultScheme, realm ->
//                call.respond(ApiResponse.ErrorResponse("Invalid authentication credentials"))
//            }
        }
    }
}
