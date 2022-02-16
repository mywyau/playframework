package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Injecting

trait BaseControllerSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  override lazy val app: Application =
    GuiceApplicationBuilder()
      .build()

}
