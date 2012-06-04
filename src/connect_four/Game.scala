package connect_four

import connect_four._

class Game {
	val board = new Board
	var currentMarker = Markers.A
	// directions are deltas used to check board locations in the cardinal directions
	val directions = for {
	  i <- List.range(-1, 2); 
	  j <- List.range(-1, 2);
	  if !(i==0 && j==0)
	  } yield (i, j)
	  
	def checkPosition(index:Index, marker:Int, delta: (Int, Int), steps: Int): Boolean = {
	    if(steps == 0) {
	    	true
	    }
	    else {
	    	if (board.isInBounds(index) && board.posIs(marker, index)) {
	    	  val newIndex = new Index(index.row + delta._1, index.col + delta._2)
	    	  checkPosition(newIndex, marker, delta, steps-1)
	    	}
	    	else {
	    	  false
	    	}
	    }	 
	}
	  
	def isWin():Boolean = {
	  board.getPositionIndices().exists(testWinAtIndex)
	}
	
	def testWinAtIndex(index:Index): Boolean = {
	  // get the value of the board at the passed index
	  val marker = board.markerAt(index)
	  
	  // None will be returned if the index is out of bounds.
	  // In that case, or an empty marker, we do NOT have a win
	  if(marker == Some(Markers.Empty) || marker == None) {
	    false
	  }
	  else {
	    // 
	    val result = for {
	        m <- marker
	    	result <- Some(directions.exists(delta => checkPosition(index, m, delta, 4)))
	    } yield result
	    result == Some(true)
	  }
	}
	
	def getFirstEmptyRowInColumn(c: Int): Option[Int] = {
	  if(!board.isInBounds(new Index(0, c))) {
	    None
	  }
	  else {
	    def helper(row: Int): Int = {
	      if(row < 0) {
	        row
	      }
	      else {
	        val marker = markerAt(new Index(row, c))
	        if(marker == Some(Markers.Empty)) row else helper(row - 1)
	      }
	    }
	    for {
	      r <- Some(helper(board.size.height-1))
	      result <- if(r < 0) None else Some(r)
	    } yield result
	  }
	} 
	
	def toggleMarker(): Int = {
	  currentMarker = if(currentMarker == Markers.A) Markers.B else Markers.A
	  currentMarker
	}
	
	def updateBoard(index: Index): Int = {
	  board.move(currentMarker, index)
	  toggleMarker()
	}
	
	def move(mv: Move): Option[Int] = {
	  if(isWin()) {
	    None
	  }
	  else {
	    for {
	      row <- getFirstEmptyRowInColumn(mv.col)
	      result <- if(row >= 0) Some(updateBoard(new Index(row, mv.col))) else None
	    } yield result
	  }
	}
	
	def markerAt(index:Index): Option[Int] = {
	  board.markerAt(index)
	}
	
	def getCurrentMarker(): Int = {
	  currentMarker
	}
}
