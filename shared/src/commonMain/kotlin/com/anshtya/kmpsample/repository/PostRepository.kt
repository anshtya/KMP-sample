package com.anshtya.kmpsample.repository

import com.anshtya.kmpsample.db.PostDao
import com.anshtya.kmpsample.model.Post
import com.anshtya.kmpsample.network.JsonApi
import kotlinx.coroutines.flow.Flow

class PostRepository(
    private val api: JsonApi,
    private val postDao: PostDao
) {
    val posts: Flow<List<Post>> = postDao.getPosts()

    suspend fun fetchAndStorePosts() = runCatching {
        val posts = api.getPosts().getOrThrow()
        postDao.upsertPosts(posts)
    }
}