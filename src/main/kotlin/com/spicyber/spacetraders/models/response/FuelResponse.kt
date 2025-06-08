package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Agent
import com.spicyber.spacetraders.models.ShipFuel
import kotlinx.serialization.Serializable

@Serializable
data class FuelResponse(
    val data: FuelData
)

@Serializable
data class FuelData(
    val fuel: ShipFuel,
    val agent: Agent,
    val transaction: Transaction
)

@Serializable
data class Transaction(
    val waypointSymbol: String,
    val shipSymbol: String,
    val tradeSymbol: String,
    val type: String,
    val units: Int,
    val pricePerUnit: Int,
    val totalPrice: Int,
    val timestamp: String
)