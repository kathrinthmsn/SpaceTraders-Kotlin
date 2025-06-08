package com.spicyber.spacetraders.services

import com.spicyber.spacetraders.SpaceTradersApiFactory
import com.spicyber.spacetraders.models.Survey
import com.spicyber.spacetraders.models.enums.ShipStatus
import com.spicyber.spacetraders.models.request.SurveyRequest
import com.spicyber.spacetraders.models.response.ShipType
import com.spicyber.spacetraders.models.response.SurveyResponse
import kotlinx.coroutines.delay
import org.springframework.stereotype.Service

@Service
class SurveyService(apiFactory: SpaceTradersApiFactory) {

    private val api = apiFactory.getApi()
    private val navigationService: NavigationService = NavigationService(apiFactory)

    suspend fun survey(destination: String, neededGoodForContract: String) : SurveyRequest? {

        val surveyShip = api.getMyShips().data.firstOrNull { ship ->
            ship.mounts.any { it.symbol.contains("SURVEYOR", true) }
        } ?: run {
            println("⚠️ No ship with surveyor mount found")
            return null
        }

        navigationService.goToWaypoint(destination, surveyShip.symbol)

        if (api.getShip(surveyShip.symbol).data.nav.status != ShipStatus.IN_ORBIT) {
            api.orbitShip(surveyShip.symbol)
            println("🪐 Orbiting ship: ${surveyShip.symbol}")
        }

        while (true) {
            val surveyResult = try {
                api.survey(surveyShip.symbol)
            } catch (e: Exception) {
                println("❌ Survey failed: ${e.message}")
                delay(90000)
                continue
            }

            surveyResult.data.surveys
                .filter { it.deposits.any { d -> d.symbol == neededGoodForContract } }
                .sortedWith(
                    compareByDescending<Survey> { survey ->
                        survey.deposits.count { it.symbol == neededGoodForContract }
                    }.thenBy { survey ->
                        survey.deposits.size
                    }
                )
                .firstOrNull()
                ?.let { bestSurvey ->
                    println("✅ Found suitable survey with $neededGoodForContract deposits.")
                    return SurveyRequest(
                        signature = bestSurvey.signature,
                        symbol = bestSurvey.symbol,
                        deposits = bestSurvey.deposits,
                        expiration = bestSurvey.expiration,
                        size = bestSurvey.size
                    )
                }

            val cooldown = surveyResult.data.cooldown.remainingSeconds
            println("⏳ No suitable survey found. Waiting ${cooldown}s cooldown...")
            delay(cooldown * 1000L + 1000)
        }
    }
}