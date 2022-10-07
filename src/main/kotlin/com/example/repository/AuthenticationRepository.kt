package com.example.repository

import com.example.model.ApiResponse
import com.example.routes.authentication.UserParams

interface AuthenticationRepository {

    suspend fun registerUser(userParams: UserParams): ApiResponse<Any>

    suspend fun loginUser(email: String, password: String): ApiResponse<Any>
}