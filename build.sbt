
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

val zioVersion      = "2.1.9"
val zioKafkaVersion = "2.8.2"
val tapirVersion    = "1.11.7"
val enumeratumVersion = "1.7.3"
val slickVersion = "3.5.1"
val zioConfigVersion = "4.0.2"

lazy val root = (project in file("."))
  .settings(
    name := "kilogram",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"         % zioVersion,
      "dev.zio" %% "zio-streams" % zioVersion,

      "dev.zio" %% "zio-config"          % zioConfigVersion,
      "dev.zio" %% "zio-config-typesafe" % zioConfigVersion,
      "dev.zio" %% "zio-config-magnolia" % zioConfigVersion,

      "com.typesafe.slick" %% "slick"          % slickVersion,
      "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,

      "dev.zio" %% "zio-kafka"         % zioKafkaVersion,
      "dev.zio" %% "zio-kafka-testkit" % zioKafkaVersion % Test,

      "com.softwaremill.sttp.tapir" %% "tapir-netty-server-zio"  % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe"        % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion,

      "io.circe" %% "circe-core"    % "0.14.9",
      "io.circe" %% "circe-generic" % "0.14.9",
      "io.circe" %% "circe-parser"  % "0.14.9",

      "org.typelevel" %% "doobie-core"      % "1.0.0-RC13",
      "org.typelevel" %% "doobie-postgres"  % "1.0.0-RC13",
      "org.typelevel" %% "doobie-specs2"    % "1.0.0-RC13" % Test,
      "org.typelevel" %% "doobie-scalatest" % "1.0.0-RC13" % Test,

      "com.beachape" %% "enumeratum"        % enumeratumVersion,
      "com.beachape" %% "enumeratum-circe"  % enumeratumVersion,

      "org.liquibase" % "liquibase-core"  % "4.27.0",
      "org.postgresql" % "postgresql"     % "42.7.3"
    ),

    dependencyOverrides ++= Seq(
      "dev.zio"  %% "zio"            % zioVersion,
      "dev.zio"  %% "zio-streams"    % zioVersion,
      "io.circe" %% "circe-core"     % "0.14.9",
      "io.circe" %% "circe-generic"  % "0.14.9",
      "io.circe" %% "circe-parser"   % "0.14.9",
    )
  )