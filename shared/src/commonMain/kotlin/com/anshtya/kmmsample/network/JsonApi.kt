package com.anshtya.kmmsample.network

import com.anshtya.kmmsample.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class JsonApi(
    private val httpClient: HttpClient
) {
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    suspend fun getPosts() = runCatching {
        httpClient.get("${baseUrl}/posts").body<List<Post>>()
    }
}