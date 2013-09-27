package ly.jamie.games.connect_four

object Markers extends Enumeration {
  type Marker = Value
  val Empty, A, B = Value
}

case class Index(row: Int, col: Int) {
	def tuple(): (Int, Int) = (row, col)
}

case class Size(width: Int = 7, height: Int = 6)

case class BoardUpdate(board: Board, marker: Markers.Marker, position: Int)

case class Board(size: Size, length: Int, board: Seq[Markers.Marker]) {

  def this(size: Size) = this(size, size.width * size.height,
    IndexedSeq.fill(size.width * size.height)(Markers.Empty))

  def this() = this(Size())
	
	// Return a list of index objects
	def getPositionIndices(): IndexedSeq[Index] = 
	  for { row <- 0 to size.height-1
          col <- 0 to size.width-1 } yield new Index(row, col)
	
	def isEmpty: Boolean = board.forall(x => x == Markers.Empty)
	
	def isInBounds(index: Index): Boolean = index.row >= 0 && 
    index.row < size.height && index.col >= 0 && index.col < size.width 
	
	def fromIndex(index: Index): Option[Int] = 
	  if(isInBounds(index)) Some(index.row * size.width + index.col)
	  else None
	
	def move(marker: Markers.Marker, index: Index): Option[BoardUpdate] =
	  fromIndex(index).map { updatePosition(marker, _) }
	
	// Updates the given position without performing a check. 
	// @returns		Returns a pair of the marker that was put at the position and the position.
	def updatePosition(marker: Markers.Marker, position: Int): BoardUpdate = 
    BoardUpdate(Board(size, length, board.updated(position, marker)), 
                marker, position)
	
	def markerAt(index: Index): Option[Markers.Marker] =
	  for {
	    pos <- fromIndex(index)
	    m <- Some(board(pos))
	  } yield m
	
	def posIs(marker: Markers.Marker, index: Index): Boolean = {
	  val result: Option[Boolean] = for {
	    m <- markerAt(index)
	    // return the result of a check against the marker
	    result <- Some(m == marker)
	  } yield result
	  
	  result.getOrElse(false)
	}
	
	def hasMovesLeft: Boolean = board.exists{ _ == Markers.Empty }
	
}
