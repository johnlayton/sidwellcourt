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
}


//val springVersion :String by project
dependencies {
//  kapt("org.springframework.boot:spring-boot-configuration-processor:${springVersion}")
  api(project(path = ":api"))
  api(libraries["org.jetbrains.kotlinx:kotlinx-coroutines-core"])
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
