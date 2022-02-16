package views

import play.twirl.api.HtmlFormat
import views.html.indexView
import Selectors._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class IndexViewSpec extends BaseViewSpec {

  val view: indexView = app.injector.instanceOf[indexView]

  def applyView: HtmlFormat.Appendable = view.apply()

  val expectedMessages: Seq[(String, String)] =
    Seq(
      h1 -> "Welcome to Play! Hi Mikey"
    )

  "IndexView" should {

    pageWithExpectedMessages(applyView, expectedMessages)
  }
}
