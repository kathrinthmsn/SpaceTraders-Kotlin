package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Ship
import kotlinx.serialization.Serializable

@Serializable
data class MyShipResponse(
    val data: Ship
)