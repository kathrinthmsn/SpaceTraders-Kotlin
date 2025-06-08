package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable


@Serializable
data class Requirements(
    val power: Int? = 0,
    val crew: Int? = 0,
    val slots: Int? = 0
)