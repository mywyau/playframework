import play.core.PlayVersion
import sbt._

object AppDependencies {


  val app = Seq(
    "org.typelevel"                 %% "cats-core"                         % "2.7.0"
  )


  val scalatestVersion = "3.2.10"

  val test = Seq(
    "org.scalatest"                 %%  "scalatest"                        %  scalatestVersion,
    "org.scalacheck"                %%  "scalacheck"                       %  "1.15.4"              % Test,
    "org.jsoup"                     %   "jsoup"                            %  "1.14.3",
    "org.mockito"                   %   "mockito-core"                     %  "4.3.1",
    "org.scalacheck"                %%  "scalacheck"                       %  "1.15.4",
    "org.scalatestplus.play"        %%  "scalatestplus-play"               %  "5.1.0"               % Test,
    "com.typesafe.play"             %%    "play-test"                      %  PlayVersion.current
  )

  def apply(): Seq[ModuleID] = app ++ test
}
