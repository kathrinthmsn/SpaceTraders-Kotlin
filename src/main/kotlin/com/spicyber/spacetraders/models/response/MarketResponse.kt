package com.spicyber.spacetraders.models.response

import kotlinx.serialization.Serializable

@Serializable
data class MarketResponse(
    val data: MarketData
) {
    @Serializable
    data class MarketData(
        val symbol: String,
        val imports: List<TradeGood> = emptyList(),
        val exports: List<TradeGood> = emptyList(),
        val exchange: List<ExchangeItem> = emptyList(),
        val transactions: List<Transaction> = emptyList(),
        val tradeGoods: List<TradeGoodDetail> = emptyList()
    )

    @Serializable
    data class TradeGood(
        val symbol: String,
        val name: String,
        val description: String
    )

    @Serializable
    data class ExchangeItem(
        val symbol: String,
        val name: String,
        val description: String
    )

    @Serializable
    data class Transaction(
        val waypointSymbol: String,
        val shipSymbol: String,
        val tradeSymbol: String,
        val type: TransactionType,
        val units: Int,
        val pricePerUnit: Int,
        val totalPrice: Int,
        val timestamp: String
    )

    @Serializable
    data class TradeGoodDetail(
        val symbol: String,
        val tradeVolume: Int,
        val type: GoodType,
        val supply: SupplyLevel,
        val purchasePrice: Int,
        val sellPrice: Int
    )

    @Serializable
    enum class TransactionType { PURCHASE, SELL }

    @Serializable
    enum class GoodType { EXCHANGE, IMPORT, EXPORT }

    @Serializable
    enum class SupplyLevel {
        SCARCE, LIMITED, MODERATE, ABUNDANT, HIGH
    }
}