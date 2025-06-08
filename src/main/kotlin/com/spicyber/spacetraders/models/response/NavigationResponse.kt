package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.ShipFuel
import com.spicyber.spacetraders.models.ShipNav
import kotlinx.serialization.Serializable

@Serializable
data class NavigationResponse(
    val data: NavigationData
)

@Serializable
data class NavigationData(
    val fuel: ShipFuel,
    val nav: ShipNav,
)