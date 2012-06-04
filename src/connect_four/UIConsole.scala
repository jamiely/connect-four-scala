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
    
    if(game.isWin()) {
      println("Someone won!")
      exit
    }
    
    askMove
  }
  
  def render() = {
    println("Board:")
    val b = game.board
    for(r <- List.range(0, b.size.height)) {
      for(c <- List.range(0,b.size.width)) {
        for {
          m <- game.markerAt(new Index(r,c))
          _ <- Some(print(markerIntToString(m)))
        } yield m
      }
      println("")
    }
    for(c <- List.range(0,b.size.width)) {
      print(c)
    }
    println("")
  }
  
  def askMove() = {
    println(markerIntToString(game.currentMarker) + "'s move?")
  }
  
  def markerIntToString(marker:Int): String = marker match {
    case Markers.Empty => "_"
    case Markers.A => "X"
    case Markers.B => "O"
    case _ => "?"
  }
}