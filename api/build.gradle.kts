import com.google.protobuf.gradle.*
import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {

    kotlin("jvm")
    kotlin("kapt")

    id("java-library")
    id("plugin-utils")

    id("com.google.protobuf") version "0.8.12"
}

dependencies {
    api(libraries["com.google.protobuf:protobuf-java"])
    api(libraries["io.grpc:grpc-core"])
    api(libraries["io.grpc:grpc-protobuf"])
    api(libraries["io.grpc:grpc-stub"])
    api(libraries["io.grpc:grpc-kotlin-stub"])
    api(libraries["javax.annotation:javax.annotation-api"])
    api(libraries["org.jetbrains.kotlin:kotlin-stdlib"])
    api(libraries["org.jetbrains.kotlinx:kotlinx-coroutines-core"])
}

/*
sourceSets {
    create("sidwellcourt") {
        proto {
            srcDir("src/sidwellcourt/proto")
        }
        java {
            srcDir(file("${projectDir}/src/sidwellcourt/grpc-java"))
            srcDir(file("${projectDir}/src/sidwellcourt/java"))
        }
        kotlin {
            srcDir(file("${projectDir}/src/sidwellcourt/grpc-kotlin"))
        }
        compileClasspath = sourceSets.getByName("main").compileClasspath
        runtimeClasspath = sourceSets.getByName("main").runtimeClasspath
    }
}
*/

protobuf {
    generatedFilesBaseDir = "${projectDir}/src"
    protoc {
        artifact = libraries["com.google.protobuf:protoc"]
    }
    plugins {
        id("grpc-java") {
            artifact = libraries["io.grpc:protoc-gen-grpc-java"]
        }
        id("grpc-kotlin") {
            artifact = libraries["io.grpc:protoc-gen-grpc-kotlin"]
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc-java")
                id("grpc-kotlin")
            }
        }
    }
}

sourceSets.named("main") {
    java {
        srcDir(file("${projectDir}/src/main/grpc-java"))
        srcDir(file("${projectDir}/src/main/java"))
    }
    kotlin {
        srcDir(file("${projectDir}/src/main/grpc-kotlin"))
    }
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
}

val SourceSet.kotlin: SourceDirectorySet
    get() = (this as HasConvention).convention.getPlugin(KotlinSourceSet::class.java).kotlin

fun SourceSet.kotlin(action: SourceDirectorySet.() -> Unit) = kotlin.action()