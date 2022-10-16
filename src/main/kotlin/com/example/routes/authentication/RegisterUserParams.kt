package com.example.routes.authentication

data class RegisterUserParams(
    val name: String,
    val email: String,
    val password: String
)