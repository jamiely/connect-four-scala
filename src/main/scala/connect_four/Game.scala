package connect_four

import connect_four._

class Move (val col: Int)

class Game {
	val board = new Board
	var currentMarker = Markers.A
	// directions are deltas used to check board locations in the cardinal directions
	val directions = (for {
	  i <- -1 to 1 
	  j <- -1 to 1
	  if !(i==0 && j==0)
	  } yield (i, j)).toList
	  
	def checkPosition(index:Index, marker:Markers.Marker, delta: (Int, Int), steps: Int): Boolean = {
	    if(steps == 0) {
	    	true
	    }
	    else if (board.isInBounds(index) && board.posIs(marker, index)) {
	    	val newIndex = new Index(index.row + delta._1, index.col + delta._2)
	    	checkPosition(newIndex, marker, delta, steps-1)
	    }
	    else {
	    	false
	    }	 
	}
	  
	def isWin:Boolean = 
	  board.getPositionIndices().exists(testWinAtIndex)
	
	
	def testWinAtIndex(index:Index): Boolean = {
	  board.markerAt(index) match {
	    case None => false
	    case Some(Markers.Empty) => false
	    case Some(m) => directions.exists(delta => checkPosition(index, m, delta, 4))  
	  }
	}
	
	def getFirstEmptyRowInColumn(c: Int): Option[Int] = {
      def helper(row: Int): Int = {
	    if (row < 0) {
	      row
  	    } else {
	      val marker = markerAt(new Index(row, c))
	      if (marker == Some(Markers.Empty)) row else helper(row - 1)
	    }
	  }
  
	  if(!board.isInBounds(new Index(0, c))) {
	    None
	  }
	  else {
	    val r = helper(board.size.height-1)
	    if(r < 0) None else Some(r)
	  }
	} 
	
	def toggleMarker(): Markers.Marker = {
	  currentMarker = if(currentMarker == Markers.A) Markers.B else Markers.A
	  currentMarker
	}
	
	def updateBoard(index: Index): Markers.Marker = {
	  board.move(currentMarker, index)
	  toggleMarker()
	}
	
	def move(mv: Move): Option[Markers.Marker] = {
	  if(isWin) {
	    None
	  }
	  else {
	    for {
	      row <- getFirstEmptyRowInColumn(mv.col)
	      result <- if(row >= 0) Some(updateBoard(new Index(row, mv.col))) else None
	    } yield result
	  }
	}
	
	def markerAt(index:Index): Option[Markers.Marker] = board.markerAt(index)
	
	def getCurrentMarker: Markers.Marker = currentMarker
	
}
