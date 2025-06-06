package com.anshtya.kmpsample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anshtya.kmpsample.model.Post

@Database(
    entities = [Post::class],
    version = 1
)
abstract class PostDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}