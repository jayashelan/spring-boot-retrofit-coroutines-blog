package nl.toefel.springbootcoroutines.client

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderRegularClient {
    @GET("/posts/{id}")
    fun post(@Path("id") id: Int): Call<PostDto>

    @GET("/todos/{id}")
    fun todo(@Path("id") id: Int): Call<TodoDto>
}

