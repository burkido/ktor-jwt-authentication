package com.example.di

import com.example.repository.AuthenticationRepository
import com.example.repository.AuthenticationRepositoryImpl

import org.koin.dsl.module

//create a module for authentication repository
val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl()
    }
}