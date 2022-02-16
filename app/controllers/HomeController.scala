package controllers

import forms.HomeForm
import model.UserData
import play.api.i18n._
import play.api.mvc._
import views.html._

import javax.inject._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(override val messagesApi: MessagesApi,
                               indexView: indexView,
                               badView: badView,
                               formProvider: HomeForm,
                               val controllerComponents: MessagesControllerComponents,
                              )() extends BaseController with I18nSupport {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  def onPageLoad() = Action { implicit request: Request[AnyContent] =>
    Ok(indexView(formProvider(), "placeholder string param", routes.HomeController.onSubmit()))
  }

  def onSubmit() = Action { implicit request: Request[AnyContent] =>

    formProvider().bindFromRequest.fold(
      formWithErrors =>
        BadRequest(badView(formWithErrors, "bad request", routes.HomeController.onPageLoad())),
      userData => {
        /* binding success, you get the actual value. */
        val newUser = UserData(userData.name, userData.age)
        val name = newUser.name
        Redirect(routes.NextPageController.onSubmit())
      }
    )
  }


}

//object Runner extends App {
//
//  private def divideXByY(x: Int, y: Int): Either[String, Int] = {
//    if (y == 0) Left("Dude, not cool cannot divide by 0")
//    else Right(x / y)
//  }
//
//  def printSomething = {
//    println(divideXByY(1, 0))
//    println(divideXByY(1, 1))
//
//    divideXByY(1, 0) match {
//      case Left(s) => println("Answer: " + s)
//      case Right(i) => println("Answer: " + i)
//    }
//  }
//
//  printSomething
//}
