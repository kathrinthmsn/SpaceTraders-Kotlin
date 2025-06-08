package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable

@Serializable
data class Waypoint(
    val symbol: String,
    val type: String,
    val systemSymbol: String,
    val x: Int,
    val y: Int,
    val orbitals: List<Orbital>? = null,
    val orbits: String? = null,
    val faction: Faction? = null,
    val traits: List<Trait>? = null,
    val modifiers: List<Modifier>? = null,
    val chart: Chart? = null,
    val isUnderConstruction: Boolean = false
)

@Serializable
data class Orbital(
    val symbol: String
)

@Serializable
data class Faction(
    val symbol: String
)

@Serializable
data class Trait(
    val symbol: String,
    val name: String,
    val description: String
)

@Serializable
data class Modifier(
    val symbol: String,
    val name: String,
    val description: String
)

@Serializable
data class Chart(
    val waypointSymbol: String? = null,
    val submittedBy: String,
    val submittedOn: String
)
