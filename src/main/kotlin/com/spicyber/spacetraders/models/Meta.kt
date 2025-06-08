package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val total: Int,
    val page: Int,
    val limit: Int
)