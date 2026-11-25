import hub.controller.{ChatController, ChatEndpoint}
import security.{AuthConfig, AuthService}
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import users.controller._
import zio._
import zio.http.Server

object Main extends ZIOAppDefault {

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    (for {
      usersController <- ZIO.service[UsersController]
      chatController  <- ZIO.service[ChatController]
      endpoints = usersController.routes ++ chatController.routes
      routes    = ZioHttpInterpreter().toHttp(endpoints)
      _ <- Server.serve(routes)
    } yield ()).provide(
      UsersController.layer,
      ZLayer.succeed(UsersEndpoint()),
      AuthService.layer,
      AuthConfig.layer,
      ChatController.layer,
      ZLayer.succeed(ChatEndpoint()),
      Server.defaultWithPort(8080)
    )
}