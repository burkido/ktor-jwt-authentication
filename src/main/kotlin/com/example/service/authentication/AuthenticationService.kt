package com.example.service.authentication

import com.example.model.User
import com.example.routes.authentication.UserParams

interface AuthenticationService {

    suspend fun registerUser(userParams: UserParams): User
}