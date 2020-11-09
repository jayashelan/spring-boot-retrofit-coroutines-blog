package nl.toefel.springbootcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import nl.toefel.springbootcoroutines.client.JsonPlaceHolderSuspendClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ServiceWithSuspendClient(val jsonPlaceHolderSuspendClient: JsonPlaceHolderSuspendClient) {

    suspend fun getOverviewSuspend(): OverviewDto {
        return withContext(Dispatchers.IO) {
            val fivePosts = (1..5).map { id ->
                async {
                    jsonPlaceHolderSuspendClient.post(id)
                }
            }
            val fiveTodos = (1..5).map { id ->
                async {
                    jsonPlaceHolderSuspendClient.todo(id)
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