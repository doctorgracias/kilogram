package users.controller

import zio._

trait UsersService {
  def sumbitLogin(name : String): Boolean
}
object UsersService {
  case class Impl() extends UsersService {
    override def sumbitLogin(name : String): Boolean = true
  }

  val layer: ULayer[UsersService] =
    ZLayer.succeed(Impl())
}
