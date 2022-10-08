package com.example.security

import io.ktor.util.*
import javax.crypto.spec.SecretKeySpec

private val SECRET_KEY = "0611200006"
private val ALGORTIHM = "HmacSHA1"

private val HASH_KEY = hex(SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORTIHM)

fun hashPassword(password: String): String {
    val hmac = javax.crypto.Mac.getInstance(ALGORTIHM)
    hmac.init(HMAC_KEY)
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}