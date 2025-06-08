package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.*
import kotlinx.serialization.Serializable

@Serializable
data class ShipyardResponse(
    val data: ShipyardData
)

@Serializable
data class ShipyardData(
    val symbol: String,
    val shipTypes: List<ShipType>,
    val transactions: List<ShipTransaction>? = null,
    val ships: List<ShipListing>? = null,
    val modificationsFee: Int
)

@Serializable
data class ShipType(
    val type: String
)

@Serializable
data class ShipListing(
    val type: String,
    val name: String,
    val description: String,
    val supply: String,
    val activity: String,
    val purchasePrice: Int,
    val modules: List<ShipModule>,
    val mounts: List<ShipMount>,
    val crew: ShipCrew
)