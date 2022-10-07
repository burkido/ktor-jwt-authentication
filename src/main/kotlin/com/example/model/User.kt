package com.example.model

/**
 * @param authenticationToken will be created each time user want to authenticate
 * */
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: String,
    var authenticationToken: String? = null
)
