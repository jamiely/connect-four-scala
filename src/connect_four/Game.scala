package connect_four

import connect_four._

class Game {
	val board = new Board
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
}
