import sbt._

object Dependencies {

  lazy val sparkVersion = "2.0.0"

  val sparkDeps = Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
    "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
    "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided"
  )

}