pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenLocal()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://jitpack.io")
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "com.google.protobuf") {
        useModule("com.google.protobuf:protobuf-gradle-plugin:${requested.version}")
      }
      if (requested.id.id == "com.github.johnrengelman.shadow") {
        useModule("com.github.jengelman.gradle.plugins:shadow:${requested.version}")
      }
    }
  }
}

rootProject.name = "sidwellcourt"

include("api", "client", "sal", "app")

//ext.isCiServer = System.getenv().containsKey("CI")
//
//buildCache {
//  local {
//    enabled = !isCiServer
//  }
//  remote(HttpBuildCache) {
//    url = 'http://' + System.getenv().getOrDefault("CIRRUS_HTTP_CACHE_HOST", "localhost:12321") + "/"
//    enabled = isCiServer
//    push = true
//  }
//}