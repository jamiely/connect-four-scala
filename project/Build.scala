import sbt._
object MyBuild extends Build {
  lazy val root = Project("connect_four", 
                          file(".")
                          )
}

