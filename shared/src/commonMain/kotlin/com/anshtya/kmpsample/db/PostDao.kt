package com.anshtya.kmpsample.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.anshtya.kmpsample.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getPosts(): Flow<List<Post>>

    @Upsert
    suspend fun upsertPosts(posts: List<Post>)
}