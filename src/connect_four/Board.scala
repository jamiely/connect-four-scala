package connect_four

import connect_four._

class Index(val row: Int, val col: Int) {
	def tuple(): (Int, Int) = (row, col)
}

class Size(val width: Int = 7, val height: Int = 6)

class Board(val size: Size = new Size()) {
	val length = size.width * size.height
	val board = (for (_ <- 0 to length-1) yield Markers.Empty).toArray
	
	// Return a list of index objects
	def getPositionIndices() = {
	  for {
		  row <- 0 to size.height-1
		  col <- 0 to size.width-1
	  }
	  	yield new Index(row, col)
	}
	
	def isEmpty = board.forall(x => x == Markers.Empty)
	
	def isInBounds(index: Index) = {
	  val row = index.row
	  val col = index.col 
	  row >= 0 && row < size.height && col >= 0 && col < size.width 
	}
	
	def fromIndex(index: Index): Option[Int] = {
	  if(isInBounds(index)) {
		  Some(index.row * size.width + index.col)
	  }
	  else None
	}
	
	def move(marker: Markers.Marker, index: Index): Boolean = {
	  val pos: Option[Int] = for {
	    pos <- fromIndex(index)
	    _ <- Some(updatePosition(marker, pos))
	  } yield pos
	  !pos.isEmpty
	}
	
	// Updates the given position without performing a check. 
	// @returns		Returns a pair of the marker that was put at the position and the position.
	def updatePosition(marker: Markers.Marker, position: Int) = {
	  board(position) = marker
	  (marker, position)
	}
	
	def markerAt(index: Index): Option[Markers.Marker] = {
	  for {
	    pos <- fromIndex(index)
	    m <- Some(board(pos))
	  } yield m
	}
	
	def posIs(marker: Markers.Marker, index: Index) = {
	  val result: Option[Boolean] = for {
	    m <- markerAt(index)
	    // return the result of a check against the marker
	    result <- Some(m == marker)
	  } yield result
	  
	  result.getOrElse(false)
	}
	
	def hasMovesLeft = board.exists(x => x == Markers.Empty)
	
}
