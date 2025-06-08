package com.spicyber.spacetraders.models.request

import kotlinx.serialization.Serializable

@Serializable
data class CargoRequest(
    val symbol: String,
    val units: Int
)