package connect_four

object UIConsole extends Application {
  println("Starting game. Enter a column number to move to. Use 0-based indices.")
  val game = new Game
  render
  askMove
  
  for(ln <- io.Source.stdin.getLines ) {
    if(ln == "exit" || ln == "quit") exit
    
    println("Your move was: ")
    println(ln)
    
    val column = Integer.parseInt(ln)
    game.move(new Move(column))
    
    render
    
    if(game.isWin) {
      println("Someone won!")
      exit
    }
    
    askMove
  }
  
  def render = {
    println("Board:")
    val b = game.board
    val xRange = 0 to b.size.width-1
    for(r <- 0 to b.size.height-1) {
      for(c <- xRange) {
        for {
          m <- game.markerAt(new Index(r,c))
        } print(markerIntToString(m))
      }
      println("")
    }
    for(c <- xRange) {
      print(c)
    }
    println("")
  }
  
  def askMove = {
    println(markerIntToString(game.currentMarker) + "'s move?")
  }
  
  def markerIntToString(marker:Markers.Marker): String = marker match {
    case Markers.Empty => "-"
    case Markers.A => "X"
    case Markers.B => "O"
    case _ => "?"
  }
}