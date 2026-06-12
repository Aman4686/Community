package com.example.community

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform