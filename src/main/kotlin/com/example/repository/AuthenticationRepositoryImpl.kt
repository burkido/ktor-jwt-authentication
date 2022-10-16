package com.example.repository

import com.example.model.ApiResponse
import com.example.routes.authentication.LoginUserParams
import com.example.routes.authentication.RegisterUserParams
import com.example.security.JwtConfig
import com.example.service.authentication.AuthenticationService
import org.koin.java.KoinJavaComponent.inject

class AuthenticationRepositoryImpl: AuthenticationRepository {

    //inject authentication service
    private val authenticationService: AuthenticationService by inject(AuthenticationService::class.java)

    override suspend fun registerUser(userParams: RegisterUserParams): ApiResponse<Any> {
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

    override suspend fun loginUser(loginUserParams: LoginUserParams): ApiResponse<Any> {
        val user = authenticationService.loginUser(loginUserParams)
        return if (user != null) {
            val token = JwtConfig.instance.createAccessToken(user.id)
            user.authenticationToken = token
            ApiResponse.SuccessResponse(
                data = user,
                message = "User logged in successfully"
            )
        } else {
            ApiResponse.ErrorResponse(message = "Something went wrong")
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return authenticationService.checkUserIfExistsByEmail(email) != null
    }
}

