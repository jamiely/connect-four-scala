name := "connect_four"

organization := "ly.jamie.games"

version := "0.1.1"

scalaVersion := "2.11.11"

crossScalaVersions := Seq("2.10.6", "2.11.11", "2.12.3")

libraryDependencies += {
  val specsVersion = scalaBinaryVersion.value match {
    case "2.12" => "3.9.5"
    case _ => "3.6.5"
  }
  "org.specs2" %% "specs2-core" % specsVersion % Test
}

scalacOptions ++= Seq("-unchecked", "-deprecation")

