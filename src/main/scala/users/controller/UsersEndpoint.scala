package users.controller

import sttp.tapir._

case class UsersEndpoint() {
  val baseAuth = "auth"

  val login = endpoint
    .get
    .in("login")
    .out(stringBody)

  val profile = endpoint
    .get
    .in(baseAuth / "me")
    .in(auth.bearer[String]())
    .out(stringBody)
}
