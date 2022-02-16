package forms

import model.UserData
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

class HomeForm {

  def apply(): Form[UserData] =
    Form(
      mapping(
        "name" -> nonEmptyText,
        "age" -> number(min = 0, max = 100)
      )(UserData.apply)(UserData.unapply)
    )

}
