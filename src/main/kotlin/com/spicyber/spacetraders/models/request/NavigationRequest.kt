package com.spicyber.spacetraders.models.request

import kotlinx.serialization.Serializable

@Serializable
data class NavigationRequest(
    val waypointSymbol: String
)