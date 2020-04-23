package com.github.johnlayton.sidwellcourt

import io.grpc.ManagedChannelBuilder
import org.github.johnlayton.sidwellcourt.SidwellcourtServiceGrpcKt
import org.github.johnlayton.sidwellcourt.SidwellcourtServiceGrpcKt.SidwellcourtServiceCoroutineStub

object SidwellcourtConfiguration {
    val GRPC_PORT = 8239

    fun createClient(): SidwellcourtClient {
        val channel = ManagedChannelBuilder.forAddress("sidwellcourt", GRPC_PORT)
                .enableRetry()
                .maxRetryAttempts(3)
                .build()
        val stub = SidwellcourtServiceCoroutineStub(channel)
        return SidwellcourtClientImpl(stub)
    }
}