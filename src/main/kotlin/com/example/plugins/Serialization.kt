package com.example.plugins


import io.ktor.features.*
import io.ktor.application.*
import io.ktor.jackson.*

//ktorjwt!
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson()
    }
}
