package com.example.database.tables

import org.jetbrains.exposed.sql.Table

object User : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val password = varchar("password", 50)

    override val primaryKey = PrimaryKey(id)
}