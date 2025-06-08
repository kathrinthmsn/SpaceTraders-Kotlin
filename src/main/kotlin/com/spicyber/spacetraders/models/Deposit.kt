package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable

@Serializable
data class Deposit(
    val symbol: String,
)