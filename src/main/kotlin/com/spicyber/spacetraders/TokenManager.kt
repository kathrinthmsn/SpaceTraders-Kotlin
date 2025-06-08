package com.spicyber.spacetraders

import org.springframework.stereotype.Component
import java.io.File

@Component
class TokenManager {
    private var currentToken: String? = null
    private val tokenDir = File("tokens").apply { mkdirs() }
    private val tokenFile = File(tokenDir,"token.txt")

    fun loadToken(): String? {
        return if (tokenFile.exists()) {
            tokenFile.readText().takeIf { it.isNotBlank() }
        } else {
            null
        }
    }

    fun saveToken(token: String) {
        tokenFile.writeText(token)
        currentToken = token
    }

    fun getCurrentToken(): String {
        return currentToken ?: loadToken() ?: throw IllegalStateException("No token available")
    }
}