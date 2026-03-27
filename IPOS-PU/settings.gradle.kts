plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "IPOS-PU"
include("app:gui")
include("app:prm")
include("app:rpt")
include("app:prm")
include("app:comms")
include("app:members")
include("app:sales")