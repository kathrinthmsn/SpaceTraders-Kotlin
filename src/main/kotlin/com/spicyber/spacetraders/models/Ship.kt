package com.spicyber.spacetraders.models

import com.spicyber.spacetraders.models.enums.ShipStatus
import kotlinx.serialization.Serializable
import java.time.Duration
import java.time.Instant

@Serializable
data class Ship(
    val symbol: String,
    val registration: ShipRegistration,
    val nav: ShipNav,
    val crew: ShipCrew,
    val modules: List<ShipModule>,
    val mounts: List<ShipMount>,
    val cargo: Cargo,
    val fuel: ShipFuel,
    val cooldown: Cooldown? = null
)


@Serializable
data class ShipRegistration(
    val name: String,
    val factionSymbol: String,
    val role: String
)


@Serializable
data class ShipNav(
    val systemSymbol: String,
    val waypointSymbol: String,
    val route: NavRoute?,
    val status: ShipStatus,
    val flightMode: String  // "CRUISE", "DRIFT", etc.
)

fun ShipNav.calculateRouteTime(): Long {
    val route = this.route ?: return 0L
    val departure = Instant.parse(route.departureTime)
    val arrival = Instant.parse(route.arrival)
    return Duration.between(departure, arrival).seconds
}

@Serializable
data class NavRoute(
    val destination: Waypoint,
    val origin: Waypoint,
    val departureTime: String,
    val arrival: String
)

@Serializable
data class ShipFuel(
    val current: Int,
    val capacity: Int,
    val consumed: FuelConsumed?
)

@Serializable
data class FuelConsumed(
    val amount: Int,
    val timestamp: String
)

@Serializable
data class ShipCrew(
    val current: Int? = null,
    val required: Int,
    val capacity: Int,
    val rotation: String? = null,
    val morale: Int? = null,
    val wages: Int? = null
)

@Serializable
data class ShipModule(
    val symbol: String,
    val capacity: Int? = null,
    val range: Int? = null,
    val name: String,
    val description: String,
    val requirements: Requirements
)

@Serializable
data class ShipMount(
    val symbol: String,
    val name: String,
    val description: String,
    val strength: Int,
    val deposits: List<String>? = null,
    val requirements: Requirements
)

@Serializable
data class CargoItem(
    val symbol: String,
    val name: String,
    val description: String,
    val units: Int
)

