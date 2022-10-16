package com.example.plugins

import com.example.di.repositoryModule
import com.example.di.serviceModule
import io.ktor.application.*
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(serviceModule)
        modules(repositoryModule)
    }
}