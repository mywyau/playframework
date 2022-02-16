package views

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Injecting
import play.twirl.api.{Html, HtmlFormat}

trait BaseViewSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {

  override lazy val app: Application =
    GuiceApplicationBuilder()
      .build()

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
