package com.spicyber.spacetraders.services

import com.spicyber.spacetraders.SpaceTradersApiFactory
import com.spicyber.spacetraders.models.request.CargoRequest
import com.spicyber.spacetraders.models.request.DeliverContractRequest
import com.spicyber.spacetraders.models.request.SurveyRequest
import kotlinx.coroutines.delay
import org.springframework.stereotype.Service

@Service
class MiningService(apiFactory: SpaceTradersApiFactory) {

    private val navigationService: NavigationService = NavigationService(apiFactory)
    private val contractService: ContractService = ContractService(apiFactory)
    private val surveyService: SurveyService = SurveyService(apiFactory)
    private val api = apiFactory.getApi()

    suspend fun mineAsteroid(systemSymbol: String, shipSymbol: String, neededGoodForContract: String) {
        val asteroidWayoints = api.findAsteroid(systemSymbol).data
        val destination = asteroidWayoints.get(0).symbol
        navigationService.goToWaypoint(destination, shipSymbol)
        val surveyRequest = surveyService.survey(destination, neededGoodForContract)

        var cargo = api.getShipCargo(shipSymbol)
        while(cargo.data.units != cargo.data.capacity) {
            val extractedGood = api.extractOresAndMinerals(surveyRequest, shipSymbol)
            println("⛏️ Successfully extracted: ${extractedGood.data.extraction.yield.symbol} (${extractedGood.data.extraction.yield.units} units)")
            val cooldown = extractedGood.data.cooldown.remainingSeconds
            println("⏳ $shipSymbol is sleepy after extracting. Waiting ${cooldown}s cooldown...")
            delay(cooldown * 1000L + 1000)

            if(extractedGood.data.extraction.yield.symbol != neededGoodForContract) {
                api.jettisonCargo(CargoRequest(extractedGood.data.extraction.yield.symbol, extractedGood.data.extraction.yield.units), shipSymbol)
                println("\uD83D\uDDD1\uFE0F Good thrown away: ${extractedGood.data.extraction.yield.symbol} (${extractedGood.data.extraction.yield.units} units)")
            }
            cargo = api.getShipCargo(shipSymbol)
        }
        val deliverContractRequest = DeliverContractRequest(shipSymbol, neededGoodForContract, cargo.data.capacity)
        contractService.deliverGoodForContract(deliverContractRequest, shipSymbol)
    }
}