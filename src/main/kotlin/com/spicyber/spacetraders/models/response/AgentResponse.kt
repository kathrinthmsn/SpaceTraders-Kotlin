package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Agent
import kotlinx.serialization.Serializable

@Serializable
data class AgentResponse(
    val data: Agent
)