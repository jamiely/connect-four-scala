package ly.jamie.games.connect_four

// scalastyle:off regex
object UIConsole extends UIConsoleLike {
  override def readLine: String = scala.Console.readLine
}

