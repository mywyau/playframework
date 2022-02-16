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
class NextPageController @Inject()(override val messagesApi: MessagesApi,
                                   nextPage: nextPageView,
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
    Ok(nextPage(formProvider(), "placeholder string param", routes.HomeController.onSubmit()))
  }

  def onSubmit() = Action { implicit request: Request[AnyContent] =>

    formProvider().bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(badView(formWithErrors, "bad request", routes.HomeController.onPageLoad()))
      },
      userData => {
        /* binding success, you get the actual value. */
        val newUser = UserData(userData.name, userData.age)
        val name = newUser.name
        Redirect(routes.NextPageController.onSubmit())
      }
    )
  }


}

