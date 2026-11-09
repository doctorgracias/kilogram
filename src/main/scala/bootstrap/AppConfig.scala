package bootstrap

import zio.config.magnolia.deriveConfig
import zio.config.typesafe.TypesafeConfigProvider
import zio.{ZIO, ZLayer}

case class AppConfig(port: Int)

object AppConfig {
  val layer: ZLayer[Any, Throwable, AppConfig] =
    ZLayer.fromZIO(
      ZIO.attempt(
        TypesafeConfigProvider
          .fromResourcePath()
          .nested("app")
          .load(deriveConfig[AppConfig])
      ).flatten
    )
}