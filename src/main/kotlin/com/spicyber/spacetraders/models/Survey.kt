package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable

@Serializable
data class Survey(
    val signature: String,
    val symbol: String,
    val deposits: List<Deposit>,
    val expiration: String,
    val size: String,
)