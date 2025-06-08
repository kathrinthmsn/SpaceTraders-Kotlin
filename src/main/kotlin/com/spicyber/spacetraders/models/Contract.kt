package com.spicyber.spacetraders.models

import kotlinx.serialization.Serializable

@Serializable
data class Contract(
    val id: String,
    val factionSymbol: String,
    val type: ContractType,
    val terms: ContractTerms,
    val accepted: Boolean,
    val fulfilled: Boolean,
    val expiration: String,
    val deadlineToAccept: String
) {
    enum class ContractType { PROCUREMENT, TRANSPORT, SHUTTLE }

    @Serializable
    data class ContractTerms(
        val deadline: String,
        val payment: Payment,
        val deliver: List<Delivery>
    ) {
        @Serializable
        data class Payment(
            val onAccepted: Int,
            val onFulfilled: Int
        )
        @Serializable
        data class Delivery(
            val tradeSymbol: String,
            val destinationSymbol: String,
            val unitsRequired: Int,
            val unitsFulfilled: Int
        )
    }
}