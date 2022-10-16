package com.example.di

import com.example.service.authentication.AuthenticationService
import com.example.service.authentication.AuthenticationServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single<AuthenticationService> {
        AuthenticationServiceImpl()
    }
}