package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Meta
import com.spicyber.spacetraders.models.Waypoint
import kotlinx.serialization.Serializable

@Serializable
data class WaypointsResponse(
    val data: List<Waypoint>,
    val meta: Meta
)