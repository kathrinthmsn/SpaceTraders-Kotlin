package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable


@Serializable
data class Cooldown(
    val shipSymbol: String,
    val totalSeconds: Int,
    val remainingSeconds: Int,
    val expiration: String? = null
)