package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Agent
import com.spicyber.spacetraders.models.Ship
import kotlinx.serialization.Serializable

@Serializable
data class ShipPurchaseResponse(
    val data: ShipPurchaseData
)

@Serializable
data class ShipPurchaseData(
    val agent: Agent,
    val ship: Ship,
    val transaction: ShipTransaction
)

@Serializable
data class ShipTransaction(
    val waypointSymbol: String,
    val shipSymbol: String,
    val shipType: String,
    val price: Int,
    val agentSymbol: String,
    val timestamp: String
)
