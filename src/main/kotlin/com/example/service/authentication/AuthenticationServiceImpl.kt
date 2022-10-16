package com.example.service.authentication

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.tables.UserTable
import com.example.model.User
import com.example.routes.authentication.LoginUserParams
import com.example.routes.authentication.RegisterUserParams
import com.example.security.hashPassword
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class AuthenticationServiceImpl : AuthenticationService {

    override suspend fun registerUser(userParams: RegisterUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[name] = userParams.name
                it[email] = userParams.email
                it[password] = hashPassword(userParams.password)
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun loginUser(loginUserParams: LoginUserParams): User? {
        val hashedPassword = hashPassword(loginUserParams.password)
        val userRow = dbQuery {
            UserTable.select {
                (UserTable.email eq loginUserParams.email) and (UserTable.password eq hashedPassword)
            }.firstOrNull()
        }
        return rowToUser(userRow)
    }

    override suspend fun checkUserIfExistsByEmail(email: String) = dbQuery {
        UserTable.select {
            UserTable.email.eq(email)
        }.mapNotNull { rowToUser(it) }.singleOrNull()
    }

    private fun rowToUser(resultRow: ResultRow?): User? {
        return if (resultRow == null) {
            null
        } else
            User(
                id = resultRow[UserTable.id],
                name = resultRow[UserTable.name],
                email = resultRow[UserTable.email],
                password = resultRow[UserTable.password],
                createdAt = resultRow[UserTable.createdAt].toString()
            )
    }
}