package com.anshtya.kmmsample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform