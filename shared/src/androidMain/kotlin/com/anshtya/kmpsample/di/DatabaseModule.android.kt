package com.anshtya.kmpsample.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anshtya.kmpsample.db.PostDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module = module {
    single<PostDatabase> { getDatabaseBuilder(ctx = get()).build() }
}

private fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<PostDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("post_db.db")
    return Room.databaseBuilder<PostDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}