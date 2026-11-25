package hub.controller

import zio._

trait ChatService {
}
object ChatService {
  case class Impl() extends ChatService {

  }

  val layer: ULayer[ChatService] =
    ZLayer.succeed(Impl())
}
