package users.controller

import sttp.tapir.server.ServerEndpoint
import sttp.tapir.ztapir._
import security.AuthService
import zio._

trait UsersController {
  def routes: List[ServerEndpoint[Any, Task]]
}

object UsersController {
  case class Impl(endpoints: UsersEndpoint, authService: AuthService) extends UsersController {

    private val loginRoute = endpoints
      .login
      .zServerLogic { _ =>
        ZIO.log("login called") *> ZIO.succeed("lox")
      }

    override def routes: List[ServerEndpoint[Any, Task]] = List(loginRoute)
  }

  val layer: ZLayer[UsersEndpoint with AuthService, Nothing, UsersController] =
    ZLayer.fromZIO(for {
      endpoints <- ZIO.service[UsersEndpoint]
      authService <- ZIO.service[AuthService]
    } yield Impl(endpoints, authService))
}