package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Contract
import kotlinx.serialization.Serializable

@Serializable
data class AcceptContractResponse(val data: AcceptContractData)

@Serializable
data class AcceptContractData(
    val contract: Contract
)