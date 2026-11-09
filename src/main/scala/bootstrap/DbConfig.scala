package bootstrap

import zio.config.magnolia.deriveConfig
import zio.config.typesafe.TypesafeConfigProvider
import zio.{ZIO, ZLayer}

case class DbConfig(url: String, user: String, password: String)

object DbConfig {
  val layer: ZLayer[Any, Throwable, DbConfig] =
    ZLayer.fromZIO(
      ZIO.attempt(
        TypesafeConfigProvider
          .fromResourcePath()
          .nested("db")
          .load(deriveConfig[DbConfig])
      ).flatten
    )
}