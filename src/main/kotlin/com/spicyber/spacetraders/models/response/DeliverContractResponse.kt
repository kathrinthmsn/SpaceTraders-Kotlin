package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Cargo
import com.spicyber.spacetraders.models.Contract
import kotlinx.serialization.Serializable

@Serializable
data class DeliverContractResponse(val data: DeliverContractData)

@Serializable
data class DeliverContractData(
    val contract: Contract,
    val cargo: Cargo
)