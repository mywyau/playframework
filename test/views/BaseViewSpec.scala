package views

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.i18n.{Messages, MessagesApi}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.mvc.{AnyContentAsEmpty, MessagesControllerComponents}
import play.api.test.{FakeRequest, Injecting}
import play.twirl.api.{Html, HtmlFormat}

trait BaseViewSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

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

  def asDocument(html: Html): Document = Jsoup.parse(html.toString())

  def pageWithExpectedMessages(view: HtmlFormat.Appendable, checks: Seq[(String, String)]): Unit = checks.foreach {
    case (cssSelector, message) =>

      s"have element with cssSelector '$cssSelector'" must {

        s"have message '$message'" in {
          val doc = asDocument(view)
          val elem = doc.select(cssSelector)
          elem.first.text() mustBe message
        }
      }
  }

}
