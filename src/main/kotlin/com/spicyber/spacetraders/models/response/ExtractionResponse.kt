package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Cargo
import com.spicyber.spacetraders.models.Cooldown
import kotlinx.serialization.Serializable

@Serializable
data class ExtractionResponse(
    val data: ExtractionData
)

@Serializable
data class ExtractionData(
    val cooldown: Cooldown,
    val extraction: Extraction,
    val cargo: Cargo
)

@Serializable
data class Extraction(
    val shipSymbol: String,
    val yield: Yield,
)

@Serializable
data class Yield(
    val symbol: String,
    val units: Int,
)