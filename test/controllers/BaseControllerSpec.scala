package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.i18n.{Messages, MessagesApi}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.mvc.{AnyContentAsEmpty, MessagesControllerComponents}
import play.api.test.{FakeRequest, Injecting}

trait BaseControllerSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  override lazy val app: Application =
    GuiceApplicationBuilder()
      .build()

  private def makeFakeRequest(sessionKvs: (String, String)*) = {
    FakeRequest("GET", "/foo")
  }

  lazy val fakeRequest: FakeRequest[AnyContentAsEmpty.type] = makeFakeRequest()

  lazy val messagesControllerComponents: MessagesControllerComponents = app.injector.instanceOf[MessagesControllerComponents]
  implicit lazy val messagesApi: MessagesApi = app.injector.instanceOf[MessagesApi]
  implicit lazy val messages: Messages = messagesApi.preferred(fakeRequest)
}
