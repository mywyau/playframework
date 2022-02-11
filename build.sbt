import sbt.Keys.libraryDependencies

organization := "mikey.org"

version := "1.0-SNAPSHOT"

lazy val root =
  (project in file("."))
    .enablePlugins(PlayScala)
    .settings(
      name := """playframework""",
      scalaVersion := "2.13.8",
      TwirlKeys.templateImports ++= Seq(
        //     "views.ViewUtils._",
        //      "models.Mode",
        //      "controllers.routes._" etc.
      ),
      libraryDependencies += guice,
      libraryDependencies ++= AppDependencies()
    )




// Adds additional packages into Twirl
//TwirlKeys.templateImports += "mikey.org.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "mikey.org.binders._"
