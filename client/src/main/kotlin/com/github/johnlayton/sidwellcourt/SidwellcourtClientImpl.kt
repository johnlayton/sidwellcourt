package com.github.johnlayton.sidwellcourt

import org.github.johnlayton.sidwellcourt.SidwellcourtRequest
import org.github.johnlayton.sidwellcourt.SidwellcourtServiceGrpcKt.SidwellcourtServiceCoroutineStub

class SidwellcourtClientImpl(private val stub: SidwellcourtServiceCoroutineStub) : SidwellcourtClient {
    override suspend fun sayHello(id: Long, name: String): String {
        val request = SidwellcourtRequest.newBuilder()
                .setId(id)
                .setName(name)
                .build()
        return stub.sayHello(request).message
    }
}