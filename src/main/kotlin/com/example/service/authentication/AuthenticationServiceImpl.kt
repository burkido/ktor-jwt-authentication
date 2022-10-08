package com.example.service.authentication

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.tables.UserTable
import com.example.database.tables.UserTable.password
import com.example.model.User
import com.example.routes.authentication.UserParams
import com.example.security.hashPassword
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class AuthenticationServiceImpl : AuthenticationService {

    override suspend fun registerUser(userParams: UserParams): User? {
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