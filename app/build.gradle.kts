import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
//  `kotlin-dsl`
//  `java-library`
//  `maven-publish`

//  java
//  publishing
//  id("maven-publish")
  kotlin("jvm")
  id("java-library")
//  kotlin("kapt")
//  kotlin("plugin.spring")
//
//  id("io.spring.dependency-management")

//  id("plugin-group")
//  id("plugin-version")

//  id("idea")

  id("application")
  id("com.github.johnrengelman.shadow") version "5.2.0"
  id("com.google.cloud.tools.jib")
}

//val springVersion :String by project
dependencies {
//  kapt("org.springframework.boot:spring-boot-configuration-processor:${springVersion}")
  implementation(project(path = ":api"))
  implementation(project(path = ":sal"))
  implementation(libraries["io.grpc:grpc-netty"])
  implementation(libraries["io.grpc:grpc-services"])
  implementation(libraries["org.jetbrains.kotlinx:kotlinx-coroutines-core"])
//  implementation(libraries["org.jetbrains.kotlinx:kotlinx-coroutines-core"])
//  api(project(path = ":api", configuration = "runtime"))
//  api("au.com.mebank.integration.soap", "api", "+")
//  implementation("org.springframework.boot:spring-boot-starter-webflux:${springVersion}")
}

//kapt {
//  showProcessorTimings = true
//}

publishing {
  publications {
    create<MavenPublication>("default") {
      from(components["java"])
    }
  }
}

//shadowJar {
//  baseName = 'example'
//  configurations = [
//    project.configurations.compileClasspath,
//    project.configurations.runtimeClasspath
//  ]
//}

application {
  mainClassName = "com.github.johnlayton.sidwellcourt.SidwellcourtServerKt"
}
//mainClassName = "com.github.johnlayton.sidwellcourt.SidwellcourtServer.kt"

tasks {
  named<ShadowJar>("shadowJar") {
    archiveBaseName.set("sidwellcourt")
//    mergeServiceFiles()
    manifest {
      attributes(mapOf("Main-Class" to "com.github.johnlayton.sidwellcourt.SidwellcourtServerKt"))
    }
//    configurations.add(configurations.compileClasspath)
//    configurations = listOf(
//      project.configurations.compileClasspath,
//      project.configurations.runtimeClasspath
//    )
  }
}

tasks.getByName<Jar>("jar") {
  enabled = true
  manifest {
    attributes.put("Main-Class", "com.github.johnlayton.sidwellcourt.SidwellcourtServerKt")
  }
}

//tasks.create<CreateStartScripts>("sidwellcourtServer") {
//  mainClassName = "com.github.johnlayton.sidwellcourt.SidwellcourtServerKt"
//  applicationName = "sidwellcourt-server"
////  outputDir = file("${project.buildDir}/bin")
//  outputDir = file("${project.rootProject.projectDir}/bin")
////  classpath = tasks.shadowJar.get().archiveFile.get()
//}

jib {
  to {
    image = "johnlayton/sidwellcourt"
    credHelper = "osxkeychain"
  }
}