package com.spicyber.spacetraders.models.request

import com.spicyber.spacetraders.models.Deposit
import kotlinx.serialization.Serializable

@Serializable
data class SurveyRequest(
    val signature: String,
    val symbol: String,
    val deposits: List<Deposit>,
    val expiration: String,
    val size: String,
)
