package nl.toefel.springbootcoroutines

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class Controller (val serviceRegular: ServiceWithRegularClient, val serviceSuspend: ServiceWithSuspendClient){

    @GetMapping("/overview-suspend")
    suspend fun getOverviewSuspend() :OverviewDto {
        log.info("fetching overview suspend")
        return serviceSuspend.getOverviewSuspend().also {
            log.info("done fetching overview suspend")
        }
    }

    @GetMapping("/overview-regular")
    suspend fun getOverviewRegular() :OverviewDto {
        log.info("fetching overview regular")
        return serviceRegular.getOverviewRegular().also {
            log.info("done overview regular")
        }
    }

    companion object {
        val log : Logger = LoggerFactory.getLogger(Controller::class.java)
    }
}