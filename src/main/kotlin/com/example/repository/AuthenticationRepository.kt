package com.example.repository

import com.example.model.ApiResponse
import com.example.routes.authentication.LoginUserParams
import com.example.routes.authentication.RegisterUserParams

interface AuthenticationRepository {

    suspend fun registerUser(userParams: RegisterUserParams): ApiResponse<Any>

    suspend fun loginUser(loginUserParams: LoginUserParams): ApiResponse<Any>
}