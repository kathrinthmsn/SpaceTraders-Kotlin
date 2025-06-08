package com.spicyber.spacetraders.models.request

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseShipRequest(
    val shipType: String,
    val waypointSymbol: String
)