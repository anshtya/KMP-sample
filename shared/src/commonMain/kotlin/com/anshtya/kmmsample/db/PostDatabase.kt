package com.anshtya.kmmsample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anshtya.kmmsample.model.Post

@Database(
    entities = [Post::class],
    version = 1
)
abstract class PostDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}