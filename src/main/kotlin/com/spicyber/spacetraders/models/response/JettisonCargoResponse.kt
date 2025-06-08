package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Cargo
import com.spicyber.spacetraders.models.CargoItem
import kotlinx.serialization.Serializable

@Serializable
data class JettisonCargoResponse(
    val data: JettisonCargo
)

@Serializable
data class JettisonCargo(
    val cargo: Cargo
)