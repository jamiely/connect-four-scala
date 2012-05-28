package connect_four

object Markers {
  val Empty = 0
  val A = 1
  val B = 2
}

class Index(val row: Int, val col: Int) {
	def tuple(): (Int, Int) = (row, col)
}

class Size(val width: Int = 7, val height: Int = 6)

class Board(val size: Size = new Size()) {
	val board = for (_ <- List.range(1, size.width * size.height)) yield Markers.Empty
	
	// Return a list of index objects
	def getPositionIndices() = {
	  for {
		  row <- List.range(0, size.height)
		  col <- List.range(0, size.width)
	  }
	  	yield new Index(row, col)
	}
}
