package com.example.service.authentication

interface AuthenticationService {

    suspend fun registerUser(user)
}