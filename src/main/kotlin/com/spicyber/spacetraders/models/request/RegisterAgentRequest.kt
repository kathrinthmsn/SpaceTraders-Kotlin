package com.spicyber.spacetraders.models.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterAgentRequest(
    val symbol: String,
    val faction: String
)