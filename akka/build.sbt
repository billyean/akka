name := "hello-akka"

version := "1.0"

// Spark 2.2.0 doesn't support scala 2.12
scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.16",
  "org.scalatest" %% "scalatest" % "3.0.4" %  "test",
  "net.sf.py4j" % "py4j" % "0.10.4",
  "org.apache.hadoop" % "hadoop-client" % "2.8.0",
  "org.apache.spark" %% "spark-core" % "2.2.0"
)
