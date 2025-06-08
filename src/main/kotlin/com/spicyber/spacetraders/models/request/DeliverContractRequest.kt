package com.spicyber.spacetraders.models.request

import kotlinx.serialization.Serializable

@Serializable
data class DeliverContractRequest(
    val shipSymbol: String,
    val tradeSymbol: String,
    val units: Int
)