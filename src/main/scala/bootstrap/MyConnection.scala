package bootstrap

import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import zio.{ZIO, ZLayer}

object MyConnection {
  val db: Database = Database.forConfig("payments")

  val dbLayer: ZLayer[Any, Throwable, PostgresProfile.backend.JdbcDatabaseDef] = ZLayer.scoped(
    ZIO.acquireRelease(ZIO.attempt(Database.forConfig("payments")))
    (db => ZIO.succeed(db.close()))
  )
}
