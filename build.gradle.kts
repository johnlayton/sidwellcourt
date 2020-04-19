import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
  }
  dependencies {
    classpath("com.github.johnlayton", "riverhilldrive", "0.0.7")
  }
  configurations {
    classpath {
      resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
      }
    }
  }
}

plugins {
  id("base")
  id("java")

  id("maven-publish")

  kotlin("jvm") version "1.3.61" apply false
  kotlin("kapt") version "1.3.61" apply false

  kotlin("plugin.spring") version "1.3.61" apply false

  id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
  id("org.springframework.boot") version "2.2.0.RELEASE" apply false
}

repositories {
  jcenter()
  mavenLocal()
  mavenCentral()
  maven("https://jitpack.io")
}


allprojects {
  apply(plugin = "base")
  apply(plugin = "java")

  repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
  }

  dependencies {
    implementation(kotlin("stdlib-jdk8", "1.3.61"))
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }

  java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "11" // JavaVersion.VERSION_11
    }
  }
}

subprojects {
  apply(plugin = "publishing")
  apply(plugin = "plugin-version")
  apply(plugin = "plugin-group")
}

val gradleWrapperVersion: String by project
tasks {
  wrapper {
    gradleVersion = gradleWrapperVersion
    distributionType = Wrapper.DistributionType.ALL
  }
}
