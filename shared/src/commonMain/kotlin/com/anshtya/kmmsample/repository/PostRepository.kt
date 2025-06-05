package com.anshtya.kmmsample.repository

import com.anshtya.kmmsample.db.PostDao
import com.anshtya.kmmsample.model.Post
import com.anshtya.kmmsample.network.JsonApi
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