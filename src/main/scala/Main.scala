
import security.{AuthConfig, AuthService}
import sttp.tapir.server.netty.zio._
import users.controller._
import zio._


object Main extends ZIOAppDefault {

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    (for {
      usersController <- ZIO.service[UsersController]
      server <- NettyZioServer[Any]()
        .port(8080)
        .addEndpoints(usersController.routes)
        .start()
      _ <- ZIO.never
    } yield ()).provide(
      UsersController.layer,
      ZLayer.succeed(UsersEndpoint()),
      AuthService.layer,
      AuthConfig.layer
    )
}