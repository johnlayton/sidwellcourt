pluginManagement {
  repositories {
    gradlePluginPortal()
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "com.google.protobuf") {
        useModule("com.google.protobuf:protobuf-gradle-plugin:${requested.version}")
      }
    }
  }
}

rootProject.name = "amberleyway"

include("app", "api", "sal", "model")