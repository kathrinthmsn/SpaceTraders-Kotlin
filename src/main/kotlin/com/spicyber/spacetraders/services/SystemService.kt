package com.spicyber.spacetraders.services

import com.spicyber.spacetraders.SpaceTradersApi
import com.spicyber.spacetraders.SpaceTradersApiFactory
import com.spicyber.spacetraders.models.Ship
import com.spicyber.spacetraders.models.request.PurchaseShipRequest
import com.spicyber.spacetraders.models.response.MyShipsResponse
import com.spicyber.spacetraders.models.response.ShipType
import org.springframework.stereotype.Service

@Service
class SystemService(private val apiFactory: SpaceTradersApiFactory) {

    private val miningService: MiningService = MiningService(apiFactory)
    private val api = apiFactory.getApi()

    suspend fun findMiningShip(systemSymbol: String): Ship? {
        var miningShip = getMiningShip(api.getMyShips())
        if (miningShip == null) {
            val shipyards = api.findShipyards(systemSymbol).data
            shipyards.forEach { shipyard ->
                try {
                    val ships = api.viewShipsInShipyard(systemSymbol, shipyard.symbol).data
                    if (ships.shipTypes.contains(ShipType("SHIP_MINING_DRONE"))) {
                        miningShip = api.purchaseShip(PurchaseShipRequest("SHIP_MINING_DRONE", shipyard.symbol)).data.ship
                        println("Bought SHIP_MINING_DRONE: $miningShip")
                    }
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
        }
        return miningShip;
    }

    private fun getMiningShip(shipsResponse: MyShipsResponse): Ship? {
        return shipsResponse.data.find { it.registration.role == "EXCAVATOR" }
    }
}