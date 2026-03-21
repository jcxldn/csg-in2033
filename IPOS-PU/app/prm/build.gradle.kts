plugins {
  id("org.openjfx.javafxplugin") version "0.1.0"
  id("application")
}


javafx {
    version = "21"
    modules("javafx.controls", "javafx.fxml")
}

application {
    // Define the main class for the application.
    mainClass = "ac.csg.in2033.ipos.pu.prm.Main"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
}