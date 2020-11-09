package nl.toefel.springbootcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import nl.toefel.springbootcoroutines.ServiceWithRegularClient.Companion
import nl.toefel.springbootcoroutines.client.JsonPlaceHolderSuspendClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import retrofit2.awaitResponse

@Service
class ServiceWithSuspendClient(val jsonPlaceHolderSuspendClient: JsonPlaceHolderSuspendClient) {

    suspend fun getOverviewSuspend(): OverviewDto {
        return withContext(Dispatchers.IO) {
            val fivePosts = (1..5).map { id ->
                async {
                    log.info("fetching post $id")
                    jsonPlaceHolderSuspendClient.post(id).also {
                        log.info("done fetching post $id")
                    }
                }
            }
            val fiveTodos = (1..5).map { id ->
                async {
                    log.info("fetching todo $id")
                    jsonPlaceHolderSuspendClient.todo(id).also {
                        log.info("done fetching todo $id")
                    }
                }
            }

            OverviewDto(
                posts = fivePosts.awaitAll(),
                todos = fiveTodos.awaitAll(),
            )
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(ServiceWithSuspendClient::class.java)
    }
}