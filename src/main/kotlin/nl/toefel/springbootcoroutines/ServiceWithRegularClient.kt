package nl.toefel.springbootcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import nl.toefel.springbootcoroutines.client.JsonPlaceHolderRegularClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import retrofit2.awaitResponse

@Service
class ServiceWithRegularClient(val jsonPlaceHolderRegularClient: JsonPlaceHolderRegularClient) {

    suspend fun getOverviewRegular(): OverviewDto {
        return withContext(Dispatchers.IO) {
            val fivePosts = (1..5).map { id ->
                async {
                    log.info("fetching post $id")
                    jsonPlaceHolderRegularClient.post(id).awaitResponse().body()!!.also {
                        log.info("done fetching post $id")
                    }
                }
            }
            val fiveTodos = (1..5).map { id ->
                async {
                    log.info("fetching todo $id")
                    jsonPlaceHolderRegularClient.todo(id).awaitResponse().body()!!.also {
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
        val log: Logger = LoggerFactory.getLogger(ServiceWithRegularClient::class.java)
    }
}