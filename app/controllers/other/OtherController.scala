package controllers.other

import play.api.mvc._

import javax.inject._

class OtherController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def otherPageLoad(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.other.otherWorld())
  }


}
