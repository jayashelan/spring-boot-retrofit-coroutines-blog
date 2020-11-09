package nl.toefel.springbootcoroutines.client

import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderSuspendClient {
    @GET("/posts/{id}")
    suspend fun post(@Path("id") id: Int): PostDto

    @GET("/todos/{id}")
    suspend fun todo(@Path("id") id: Int): TodoDto
}

