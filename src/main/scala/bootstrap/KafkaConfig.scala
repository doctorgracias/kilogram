package bootstrap

import zio.config.magnolia.deriveConfig
import zio.config.typesafe.TypesafeConfigProvider
import zio.{ZIO, ZLayer}

case class KafkaConfig(brokers: List[String], topic: String)

object KafkaConfig {
  val layer: ZLayer[Any, Throwable, KafkaConfig] =
    ZLayer.fromZIO(
      ZIO.attempt(
        TypesafeConfigProvider
          .fromResourcePath()
          .load(deriveConfig[KafkaConfig])
      ).flatten
    )
}