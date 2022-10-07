package com.example.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: String,
    var authenticationToken: String? = null
)
