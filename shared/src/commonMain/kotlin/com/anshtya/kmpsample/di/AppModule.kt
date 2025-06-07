package com.anshtya.kmpsample.di

import com.anshtya.kmpsample.db.PostDao
import com.anshtya.kmpsample.db.PostDatabase
import com.anshtya.kmpsample.network.JsonApi
import com.anshtya.kmpsample.repository.PostRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication = startKoin {
    appDeclaration()
    modules(appModule, viewmodelModule, databaseModule)
}

val appModule = module {
    single<HttpClient> { provideHttpClient() }
    single<JsonApi> { JsonApi(httpClient = get()) }

    single<PostDao> { providePostDao(db = get()) }

    singleOf(::PostRepository)
}

private fun provideHttpClient() = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}

private fun providePostDao(db: PostDatabase): PostDao = db.postDao()