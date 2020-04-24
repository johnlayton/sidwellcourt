import com.fkorotkov.gradle.libraries.tasks.LibrariesBaseTask
import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ResolutionStrategyWithCurrent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://jitpack.io")
  }
  dependencies {
    classpath("com.github.johnlayton", "riverhilldrive", "0.0.13")
    classpath("com.fkorotkov", "gradle-libraries-plugin", "1.0")
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

  id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
  id("org.springframework.boot") version "2.2.6.RELEASE" apply false

  id("com.google.cloud.tools.jib") version "2.2.0" apply false
}

//apply(plugin = "plugin-libraries")
//apply(plugin = "com.fkorotkov.libraries")

repositories {
  jcenter()
  mavenLocal()
  mavenCentral()
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
  maven("https://jitpack.io")
}

allprojects {

  buildscript {
    repositories {
      jcenter()
      mavenLocal()
      mavenCentral()
      google()
      maven("https://dl.bintray.com/kotlin/kotlin-eap")
      maven("https://jitpack.io")
    }
  }

  repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
    google()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://jitpack.io")
  }

  apply(plugin = "base")
  apply(plugin = "java")

  apply(plugin = "plugin-libraries")
  apply(plugin = "plugin-upgrade")

//  tasks.getting(LibrariesBaseTask::class) {
//    resolutionStrategy = Action<ResolutionStrategyWithCurrent> () {
//      componentSelection {
//        all {
//          candidate.version.
//        }
////        rules.all {
////          ComponentSelection selection ->
////          boolean rejected =['dev', 'eap', 'M1'].any { qualifier ->
////            selection.candidate.version == ~ / (?i).*[.-]${ qualifier }[.\ d -]*/
////          }
////          if (rejected) {
////            selection.reject('dev version')
////          }
//      }
//    }
//  }

  dependencies {
    implementation(kotlin("stdlib-jdk8", "1.3.61"))
  }
}

subprojects {

  apply(plugin = "kotlin")
  apply(plugin = "maven-publish")
  apply(plugin = "publishing")

  apply(plugin = "plugin-version")
  apply(plugin = "plugin-group")

//  apply(plugin = "com.fkorotkov.libraries")

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

val gradleWrapperVersion: String by project
tasks {
  wrapper {
    gradleVersion = gradleWrapperVersion
    distributionType = Wrapper.DistributionType.ALL
  }
}
