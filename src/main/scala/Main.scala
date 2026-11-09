import bootstrap.database.LiquibaseMigration
import bootstrap.{AppConfig, DbConfig, KafkaConfig}
import zio._

object Main extends ZIOAppDefault {
  override def run =
    (for {
      dbCfg <- ZIO.service[DbConfig]
      appCfg <- ZIO.service[AppConfig]
      _   <- LiquibaseMigration.run
    }
    yield ()).provide(AppConfig.layer,DbConfig.layer,KafkaConfig.layer)

}