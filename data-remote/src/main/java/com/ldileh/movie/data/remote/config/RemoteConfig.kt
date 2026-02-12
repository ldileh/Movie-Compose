package com.ldileh.movie.data.remote.config

data class RemoteConfig(
    val baseUrl: String,
    val apiKey: String,
    val enableLogging: Boolean,
    val connectTimeoutSeconds: Long = 30,
    val readTimeoutSeconds: Long = 30
)