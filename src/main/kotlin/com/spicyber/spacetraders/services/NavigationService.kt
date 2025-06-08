package com.spicyber.spacetraders.services

import com.spicyber.spacetraders.SpaceTradersApi
import com.spicyber.spacetraders.SpaceTradersApiFactory
import com.spicyber.spacetraders.models.calculateRouteTime
import com.spicyber.spacetraders.models.enums.ShipStatus
import com.spicyber.spacetraders.models.request.NavigationRequest
import kotlinx.coroutines.delay
import org.springframework.stereotype.Service

@Service
class NavigationService(apiFactory: SpaceTradersApiFactory) {

    private val api = apiFactory.getApi()

    suspend fun goToWaypoint(destination: String, shipSymbol: String) {
        val ship = api.getShip(shipSymbol).data
        if (ship.nav.waypointSymbol == destination) {
            println("\uD83D\uDCCD Ship $shipSymbol is already at $destination")
            return
        }

        if (ship.nav.status != ShipStatus.IN_ORBIT) {
            api.orbitShip(shipSymbol)
            println("🪐 Orbiting ship: $shipSymbol")
        }

        val navigationResponse = api.navigateShip(NavigationRequest(destination), shipSymbol).data
        val travelTime = navigationResponse.nav.calculateRouteTime()

        println("🚀 Navigating $shipSymbol to $destination for ${travelTime}s")
        delay(travelTime * 1000L)
    }

    suspend fun refuelShip(shipSymbol: String) {
        api.refuelShip(shipSymbol)
        println("⛽ Refuel ship: $shipSymbol")
    }

    suspend fun dockShip(shipSymbol: String, destination: String) {
        api.dockShip(shipSymbol)
        println("⚓ Docked ship: $shipSymbol at $destination")
    }
}