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

  val titleParam = "I am a title passed into the view"

  def applyView(title:String): HtmlFormat.Appendable = view.apply(title)

  val expectedMessages: Seq[(String, String)] =
    Seq(
      h1 -> "Welcome to Play! Hi Mikey"
    )

  "IndexView" should {

    pageWithExpectedMessages(applyView(titleParam), expectedMessages)
  }
}
