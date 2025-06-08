package com.spicyber.spacetraders

import com.spicyber.spacetraders.models.request.*
import com.spicyber.spacetraders.models.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpaceTradersApi {

    @POST("register")
    suspend fun register(@Body request: RegisterAgentRequest): RegisterAgentResponse

    @GET("my/agent")
    suspend fun getAgent(): AgentResponse

    @GET("my/contracts")
    suspend fun viewContracts(): ContractsResponse

    @POST("my/contracts/{contractId}/accept")
    suspend fun acceptContract(@Path("contractId") contractId: String): AcceptContractResponse

    @POST("my/ships/{shipSymbol}/negotiate/contract")
    suspend fun negotiateContract(@Path("shipSymbol") shipSymbol: String): AcceptContractResponse

    @POST("my/contracts/{contractId}/deliver")
    suspend fun deliverContractGoods(@Body request: DeliverContractRequest, @Path("contractId") contractId: String): DeliverContractResponse

    @POST("my/contracts/{contractId}/fulfill")
    suspend fun fulfillContract(@Path("contractId") contractId: String): AcceptContractResponse

    @GET("systems/{systemSymbol}/waypoints/{waypointSymbol}/shipyard")
    suspend fun viewShipsInShipyard(@Path("systemSymbol") systemSymbol: String, @Path("waypointSymbol") waypointSymbol: String): ShipyardResponse

    @GET("my/ships")
    suspend fun getMyShips(): MyShipsResponse

    @POST("my/ships")
    suspend fun purchaseShip(@Body request: PurchaseShipRequest): ShipPurchaseResponse

    @GET("my/ships/{shipSymbol}")
    suspend fun getShip(@Path("shipSymbol") shipSymbol: String): MyShipResponse

    @POST("my/ships/{shipSymbol}/orbit")
    suspend fun orbitShip(@Path("shipSymbol") shipSymbol: String): ShipNavResponse

    @POST("my/ships/{shipSymbol}/navigate")
    suspend fun navigateShip(@Body request: NavigationRequest, @Path("shipSymbol") shipSymbol: String): NavigationResponse

    @POST("my/ships/{shipSymbol}/dock")
    suspend fun dockShip(@Path("shipSymbol") shipSymbol: String): ShipNavResponse

    @POST("my/ships/{shipSymbol}/refuel")
    suspend fun refuelShip(@Path("shipSymbol") shipSymbol: String): FuelResponse

    @POST("my/ships/{shipSymbol}/survey")
    suspend fun survey(@Path("shipSymbol") shipSymbol: String): SurveyResponse

    @POST("my/ships/{shipSymbol}/extract")
    suspend fun extractOresAndMinerals(@Body request: SurveyRequest?, @Path("shipSymbol") shipSymbol: String): ExtractionResponse

    @POST("my/ships/{shipSymbol}/jettison")
    suspend fun jettisonCargo(@Body request: CargoRequest, @Path("shipSymbol") shipSymbol: String): JettisonCargoResponse

    @GET("my/ships/{shipSymbol}/cargo")
    suspend fun getShipCargo(@Path("shipSymbol") shipSymbol: String): CargoResponse

    @GET("systems/{systemSymbol}/waypoints?traits=SHIPYARD")
    suspend fun findShipyards(@Path("systemSymbol") systemSymbol: String): WaypointsResponse

    @GET("systems/{systemSymbol}/waypoints?type=ENGINEERED_ASTEROID")
    suspend fun findAsteroid(@Path("systemSymbol") systemSymbol: String): WaypointsResponse

    @GET("systems/{systemSymbol}/waypoints/{waypointSymbol}/market")
    suspend fun viewMarketData(@Path("systemSymbol") systemSymbol: String, @Path("waypointSymbol") waypointSymbol: String): WaypointsResponse

}