package com.example.service.authentication

import com.example.model.User
import com.example.routes.authentication.LoginUserParams
import com.example.routes.authentication.RegisterUserParams

interface AuthenticationService {

    /**
     * @param userParams takes user based information. Not like created date, id and token.
     * @return User if exists, null otherwise
     * */
    suspend fun registerUser(userParams: RegisterUserParams): User?

    suspend fun loginUser(loginUserParams: LoginUserParams): User?

    suspend fun checkUserIfExistsByEmail(email: String): User?

}