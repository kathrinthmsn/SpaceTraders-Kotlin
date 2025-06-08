package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Contract
import com.spicyber.spacetraders.models.Meta
import kotlinx.serialization.Serializable

@Serializable
data class ContractsResponse(
    val data: List<Contract>,
    val meta: Meta? = null
)