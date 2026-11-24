package users.model

import io.circe._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

import java.time.Instant


case class Users(
                   id: Option[Long] = None,
                   username : String,
                   password_hash : String,
                   created_at : Instant
                 )

class UsersTable(tag : Tag) extends Table[Users](tag,"payment"){
  def id = column[Option[Long]]("id",O.PrimaryKey,O.AutoInc)
  def username = column[String]("username")
  def password_hash = column[String]("password_hash")
  def created_at = column[Instant]("created_at")
  override def * : ProvenShape[Users] =
  (id,username,password_hash,created_at)<>(Users.tupled,Users.unapply)
}

object Users {
  implicit val decoder : Decoder[Users] = deriveDecoder
  implicit val encoder : Encoder[Users] = deriveEncoder
}
