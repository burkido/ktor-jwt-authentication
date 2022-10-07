package com.example.model

import io.ktor.http.*

sealed class ApiResponse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK,
) {
    data class SuccessResponse<T>(
        val data: T? = null,
        val message: String? = null
    ) : ApiResponse<T>()

    data class ErrorResponse<T>(
        val data: T? = null,
        val message: String? = null
    ) : ApiResponse<T>()

}


data class Responske(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null
)