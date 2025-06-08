package com.spicyber.spacetraders.services

import com.spicyber.spacetraders.SpaceTradersApiFactory
import org.springframework.stereotype.Service

@Service
class MarketService(apiFactory: SpaceTradersApiFactory) {
    private val api = apiFactory.getApi()

    suspend fun viewMarketData() {
       // val marketData = api.viewMarketData()
    }
}