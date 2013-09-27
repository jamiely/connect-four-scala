package ly.jamie.games.connect_four

object UIConsole {
  val game = new Game

  case class State(identifier: String) 
  object InProgress extends State("in progress")
  object GameOver extends State("game over")

  def main(args: Array[String]): Unit = {
    start
    input.foreach { ln: String => 
      if(step(ln) == GameOver) exit
    }
  }

  def exit = {
    println("Exiting...")
    System.exit(0)
  }

  def step(ln: String): State = {
    render
    askMove

    println(s"Your move was: \n$ln")
    
    optionParse(ln) match {
      case Some(column) => move(column)
      case None => {
        println("Invalid move. Type exit or quit to end the game.")
        InProgress
      }
    }
  }

  def move(column: Int): State = {
    game.move(Move(column))
    render
    
    if(game.isWin) {
      println("Someone won!")
      GameOver
    }
    else InProgress
  }

  def optionParse(string: String): Option[Int] = 
    try { Some(string.toInt) }
    catch { case e: NumberFormatException => None }

  def start = {
    println("Starting game. Enter a column number to move to. Use 0-based indices. Type exit or quit to stop.")
    render
  }

  def input: Iterator[String] = Iterator.continually(Console.readLine).
      takeWhile( ! List("exit", "quit").contains(_) )
  
  def render = {
    println("Board:")
    val b = game.board
    val xRange = 0 to b.size.width-1
    for(r <- 0 to b.size.height-1) {
      for { c <- xRange
            m <- game.markerAt(new Index(r,c)) } print(markerIntToString(m))
      println("")
    }
    for(c <- xRange) {
      print(c)
    }
    println("")
  }
  
  def askMove = println(markerIntToString(game.currentMarker) + "'s move?")
  
  def markerIntToString(marker:Markers.Marker): String = marker match {
    case Markers.Empty => "-"
    case Markers.A => "X"
    case Markers.B => "O"
    case _ => "?"
  }
}
