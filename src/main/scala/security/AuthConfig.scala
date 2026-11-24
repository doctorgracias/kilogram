package security

import zio.{ZIO, ZLayer}
import zio.config.magnolia.deriveConfig
import zio.config.typesafe.TypesafeConfigProvider

case class AuthConfig(secret: String)

object AuthConfig {
  val layer: ZLayer[Any, Throwable, AuthConfig] =
    ZLayer.fromZIO(
      ZIO.attempt(
        TypesafeConfigProvider
          .fromResourcePath()
          .nested("auth")
          .load(deriveConfig[AuthConfig])
      ).flatten
    )
}
