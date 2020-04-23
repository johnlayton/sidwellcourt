package com.github.johnlayton.sidwellcourt

interface SidwellcourtClient {
    suspend fun sayHello(id: Long, name: String): String
}