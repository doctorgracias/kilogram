package security

import zio.{config, _}
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

import java.time.Instant

trait AuthService {
  def login(username: String, password: String): IO[String, String]
  def authenticate(token: String): IO[String, String]
}

object AuthService{
  final case class Impl(config : AuthConfig) extends AuthService {
    override def login(username: String, password: String): IO[String, String] =
      if (username == "admin" && password == "password")
        ZIO.succeed(generateToken(username))
      else
        ZIO.fail("Invalid credentials")

    override def authenticate(token: String): IO[String, String] =
      validateToken(token) match {
        case Right(userId) => ZIO.succeed(userId)
        case Left(err)     => ZIO.fail(err)
      }

    private def generateToken(userId: String): String =
      JWT.create()
        .withSubject(userId)
        .withExpiresAt(Instant.now().plusSeconds(3600))
        .sign(algorithm)

    private def validateToken(token: String): Either[String, String] =
      try {
        val decoded = JWT.require(algorithm).build().verify(token)
        Right(decoded.getSubject)
      } catch {
        case e: Exception => Left(e.getMessage)
      }

    private val secret    = config.secret
    private val algorithm = Algorithm.HMAC256(secret)

  }

  val layer: URLayer[AuthConfig, AuthService] =
    ZLayer.fromFunction((config: AuthConfig) => new Impl(config))
}
