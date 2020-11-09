package nl.toefel.springbootcoroutines

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class Controller (val serviceRegular: ServiceWithRegularClient, val serviceSuspend: ServiceWithSuspendClient){

    @GetMapping("/overview-regular")
    suspend fun getOverview() :OverviewDto {
        return serviceRegular.getOverviewAwait()
    }

    @GetMapping("/overview-suspend")
    suspend fun getOverviewSuspend() :OverviewDto {
        return serviceSuspend.getOverviewSuspend()
    }

    companion object {
        val log : Logger = LoggerFactory.getLogger(Controller::class.java)
    }
}