package hub.controller

import sttp.tapir.server.ServerEndpoint
import sttp.tapir.ztapir._
import security.AuthService
import sttp.capabilities
import sttp.capabilities.zio.ZioStreams
import zio._

trait ChatController {
  def routes: Seq[ZServerEndpoint[Any, Any with ZioStreams with capabilities.WebSockets]]
}

object ChatController {
  class Impl(chatEndpoint: ChatEndpoint) extends ChatController{
    val chatRoute = chatEndpoint.chat
      .zServerLogic{_ =>
        ZIO.succeed{
          input =>
            input.map(msg => s"lol + $msg")
        }
      }
    override def routes:Seq[ZServerEndpoint[Any, Any with ZioStreams with capabilities.WebSockets]] = List(chatRoute)
  }
  val layer = ZLayer.fromFunction(new Impl(_))
}