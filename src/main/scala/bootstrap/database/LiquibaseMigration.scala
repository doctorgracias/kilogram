package bootstrap.database

import bootstrap.DbConfig
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import zio.ZIO

import java.sql.DriverManager

object LiquibaseMigration {
  val run: ZIO[DbConfig, Throwable, Unit] =
    for {
      config <- ZIO.service[DbConfig]
      _ <- ZIO.attempt {
        val connection = DriverManager.getConnection(
          config.url,
          config.user,
          config.password
        )
        val database = DatabaseFactory
          .getInstance()
          .findCorrectDatabaseImplementation(new JdbcConnection(connection))
        val liquibase = new Liquibase(
          "db/changelog/db.changelog-master.xml",
          new ClassLoaderResourceAccessor(Thread.currentThread().getContextClassLoader),
          database
        )
        liquibase.update()
        connection.close()
      }
    } yield ()
}