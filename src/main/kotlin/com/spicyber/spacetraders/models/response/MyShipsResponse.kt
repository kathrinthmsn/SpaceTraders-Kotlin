package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Meta
import com.spicyber.spacetraders.models.Ship
import kotlinx.serialization.Serializable

@Serializable
data class MyShipsResponse(
    val data: List<Ship>,
    val meta: Meta
)