package com.github.johnlayton.sidwellcourt

import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import org.github.johnlayton.sidwellcourt.SidwellcourtRequest
import org.github.johnlayton.sidwellcourt.SidwellcourtResponse
import org.github.johnlayton.sidwellcourt.SidwellcourtServiceGrpcKt

fun main() {
    val serviceImpl = SidwellcourtServer()
    val server = ServerBuilder.forPort(SidwellcourtConfiguration.GRPC_PORT)
            .addService(serviceImpl)
            .addService(ProtoReflectionService.newInstance())
            .build()
            .start()
    println("Started GRPC server on ${server.port} port...")
    server.awaitTermination()
}

class SidwellcourtServer : SidwellcourtServiceGrpcKt.SidwellcourtServiceCoroutineImplBase() {
    override suspend fun sayHello(request: SidwellcourtRequest): SidwellcourtResponse {
        println("Received request ${request}")
        val result = "${request.id} - ${request.name}"
        return SidwellcourtResponse.newBuilder()
                .setMessage(result)
                .build()
    }
}