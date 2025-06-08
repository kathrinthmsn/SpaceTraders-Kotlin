package com.spicyber.spacetraders.services

import com.spicyber.spacetraders.SpaceTradersApiFactory
import com.spicyber.spacetraders.models.Contract
import com.spicyber.spacetraders.models.request.DeliverContractRequest
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ContractService(apiFactory: SpaceTradersApiFactory) {

    private val api = apiFactory.getApi()
    private val navigationService: NavigationService = NavigationService(apiFactory)

    suspend fun viewContracts(): List<Contract> {
        val contracts = api.viewContracts().data
        println("\uD83D\uDCDC Contracts: $contracts")

        val firstContract = contracts.firstOrNull() ?: return emptyList()
        val now = Instant.now()
        val deadline = Instant.parse(firstContract.terms.deadline)

        if(!firstContract.accepted && deadline.isAfter(now)) {
            val acceptedContract = api.acceptContract(firstContract.id).data
            println("✅ Contract Accepted: $acceptedContract")
        } else if (deadline.isBefore(now)) {
            val negotiatedContract = api.negotiateContract(api.getMyShips().data.get(0).symbol).data
            println("\uD83E\uDD1D Negotiated Accepted: $negotiatedContract")
        }

        return contracts
    }

    suspend fun deliverGoodForContract(deliverContractRequest: DeliverContractRequest, shipSymbol: String) {
        val contracts = api.viewContracts().data
        val firstContract = contracts.first()
        val destination = firstContract.terms.deliver.get(0).destinationSymbol
        navigationService.goToWaypoint(destination, shipSymbol)
        navigationService.dockShip(shipSymbol, destination)
        println("\uD83D\uDE80\uD83D\uDCE6 Delivering Good for contract: ${deliverContractRequest.tradeSymbol} (${deliverContractRequest.units} units)")
        api.deliverContractGoods(deliverContractRequest, firstContract.id).data
        if(firstContract.terms.deliver.get(0).unitsRequired == firstContract.terms.deliver.get(0).unitsFulfilled) {
            fulfillContract()
        }
    }

    private suspend fun fulfillContract() {
        val contracts = api.viewContracts().data
        val firstContract = contracts.first()
        println("\uD83D\uDCDC✅ Fulfilling Contract: ${firstContract.id}")
        api.fulfillContract(firstContract.id)
    }

}