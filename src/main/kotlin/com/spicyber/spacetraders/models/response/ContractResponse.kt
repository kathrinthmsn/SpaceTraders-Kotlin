package com.spicyber.spacetraders.models.response

import com.spicyber.spacetraders.models.Contract
import kotlinx.serialization.Serializable

@Serializable
data class ContractResponse(val data: Contract)