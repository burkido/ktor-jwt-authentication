package com.example.repository

import com.example.model.ApiResponse
import com.example.routes.authentication.UserParams
import com.example.security.JwtConfig
import com.example.service.authentication.AuthenticationService

class AuthenticationRepositoryImpl(
    private val authenticationService: AuthenticationService
) : AuthenticationRepository {

    override suspend fun registerUser(userParams: UserParams): ApiResponse<Any> {
       return if (isEmailExist(userParams.email)) {
            ApiResponse.ErrorResponse(message = "Email already exists")
        } else {
            val user = authenticationService.registerUser(userParams)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authenticationToken = token
                ApiResponse.SuccessResponse(
                    data = user,
                    message = "User registered successfully"
                )
            } else {
                ApiResponse.ErrorResponse(message = "Something went wrong")
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): ApiResponse<Any> {
        val user = authenticationService.checkUserIfExistsByEmail(email)
        return if (user != null) {
            if (user.password == password) {
                ApiResponse.SuccessResponse(
                    data = user,
                    message = "User logged in successfully"
                )
            } else {
                ApiResponse.ErrorResponse(
                    message = "Incorrect password"
                )
            }
        } else {
            ApiResponse.ErrorResponse(
                message = "User does not exist"
            )
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return authenticationService.checkUserIfExistsByEmail(email) != null
    }
}

