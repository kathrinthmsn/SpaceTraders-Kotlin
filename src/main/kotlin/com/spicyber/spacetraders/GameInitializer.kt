package com.spicyber.spacetraders

import com.spicyber.spacetraders.services.*
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service

@Service
class GameInitializer(
    private val registrationService: RegistrationService,
    private val contractService: ContractService,
    private val systemService: SystemService,
    private val miningService: MiningService,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        runBlocking {
            initializeGame()
        }
    }

    private suspend fun initializeGame() {
        try {
            val agentInfo = registrationService.registerOrLoadAgent().data
            val headquarters = agentInfo.headquarters
            val systemSymbol = headquarters.substringBeforeLast('-')

            val contracts = contractService.viewContracts()
            val neededGoodForContract = contracts.get(0).terms.deliver.get(0).tradeSymbol

            val miningShip = systemService.findMiningShip(systemSymbol)
            miningShip?.symbol?.let { miningService.mineAsteroid(systemSymbol, it, neededGoodForContract) }

        } catch (e: Exception) {
            log.error("Game initialization failed", e)
            throw IllegalStateException("Failed to initialize game", e)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(GameInitializer::class.java)
    }
}