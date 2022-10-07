package com.example.service.authentication

import com.example.model.User
import com.example.routes.authentication.UserParams

interface AuthenticationService {

    /**
     * @param userParams takes user based information. Not like created date, id and token.
     * @return User if exists, null otherwise
     * */
    suspend fun registerUser(userParams: UserParams): User?

    suspend fun checkUserIfExistsByEmail(email: String): User?

}