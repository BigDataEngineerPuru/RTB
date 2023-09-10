ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "AdvanceScala"
  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.4.1",
  "org.apache.spark" %% "spark-sql" % "3.4.1",
  "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "org.mockito" % "mockito-all" % "1.8.4",
  "org.scalatestplus" %% "mockito-4-11" % "3.2.16.0" % "test")


