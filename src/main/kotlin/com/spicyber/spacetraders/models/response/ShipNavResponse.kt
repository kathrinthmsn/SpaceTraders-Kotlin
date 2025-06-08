package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.ShipNav
import kotlinx.serialization.Serializable

@Serializable
data class ShipNavResponse(val data: ShipNavResp)

@Serializable
data class ShipNavResp(
    val nav: ShipNav
)