package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable

@Serializable
data class Cargo(
    val capacity: Int,
    val units: Int,
    val inventory: List<CargoItem>
)