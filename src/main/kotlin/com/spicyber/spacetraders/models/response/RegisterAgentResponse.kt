package com.spicyber.spacetraders.models.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterAgentResponse(
    val data: RegisterAgentData
)

@Serializable
data class RegisterAgentData(
    val token: String
)