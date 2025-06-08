package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    val accountId: String,
    val symbol: String,
    val headquarters: String,
    val credits: Long,
    val startingFaction: String,
    val shipCount: Int
)