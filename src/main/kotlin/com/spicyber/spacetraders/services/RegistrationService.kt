package com.spicyber.spacetraders.services

import com.spicyber.spacetraders.SpaceTradersApiFactory
import com.spicyber.spacetraders.TokenManager
import com.spicyber.spacetraders.models.request.RegisterAgentRequest
import com.spicyber.spacetraders.models.response.AgentResponse
import com.spicyber.spacetraders.models.response.RegisterAgentResponse
import org.springframework.stereotype.Service
import java.io.File

@Service
class RegistrationService(
    private val apiFactory: SpaceTradersApiFactory,
    private val tokenManager: TokenManager
) {
    private val tokenDir = File("tokens").apply { mkdirs() }
    private val accountFile = File(tokenDir,"/account_token.txt")
    private val api = apiFactory.getApi()

    suspend fun registerOrLoadAgent(): AgentResponse {
        return try {
            api.getAgent()
        } catch (e: Exception) {
            // If failed, register new agent
            tokenManager.saveToken(accountFile.readText())
            val request = RegisterAgentRequest("SPICYBER", "COSMIC")
            val response = apiFactory.getApi().register(request)
            println("Registered new agent." )
            tokenManager.saveToken(response.data.token)
            api.getAgent()
        }
    }
}