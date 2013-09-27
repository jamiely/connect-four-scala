package connect_four.specs

import ly.jamie.games.connect_four._
import org.specs2.mutable._

class BoardSpec extends Specification {
	"A new board" should {
	  isolated
	  
	  val board = new Board
      "have a height of 6, width of 7, and length of 42" in {
	    board.size.width == 7 and 
	    		board.size.height == 6 and
	    		board.length == 42
      }
	  
  	  "be empty" in {
  		  board.isEmpty must beEqualTo(true)
	  }

	  
	  "have empty spaces" in {
		  board.board.forall(marker => marker == Markers.Empty)
	   }
	  
	   "have appropriate position indices" in {
		   val indices = board.getPositionIndices().map(_.tuple)
		   val example = List((0, 0), (0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (2, 0), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (3, 0), (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (4, 0), (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (5, 0), (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6))
		   
		   indices must containAllOf(example)
	   }
	   
	   "test for bounded indices" in {
			def bounded(r: Int, c: Int) = board.isInBounds(new Index(r, c))
			bounded(0, 0) and
				! bounded(6, 0) and
				! bounded(0, 7) and
				! bounded(-1, 0) and 
				! bounded(0, -1) and
				bounded(3,3)
		}
	   
	   "convert index objects to array indices" in {
			def convert(r: Int, c: Int) = board.fromIndex(new Index(r, c))
			
			convert(0, 0) == Some(0) and
				convert(0, 3) == Some(3) and
				convert(0, 6) == Some(6) and 
				convert(1, 0) == Some(7) and
				convert(1, 1) == Some(8) and
				convert(2, 0) == Some(14) and
				convert(2, 1) == Some(15) and
				convert(5, 6) == Some(41) and
				convert(-1, 0) == None
		}

		"return a marker when updatePosition called" in {
			board.updatePosition(Markers.A, 0) == (Markers.A, 0) and
				board.updatePosition(Markers.A, 41) == (Markers.A, 41)
		}

		"return true when move is valid" in {
			val index = new Index(0, 2)
			board.move(Markers.A, index) must beEqualTo(true)
		}
		
		"return appropriate marker after move" in {
			val index = new Index(0, 2)
			
			board.move(Markers.A, index)
			
			board.markerAt(index) must beSome(Markers.A)
		}
		"return appropriate marker after move 2" in {
			val index = new Index(5, 6)
			board.move(Markers.A, index)
			
			board.markerAt(index) must beSome(Markers.A)
		}	
		
		"should return posIs true when appropriate" in {
			val index = new Index(0, 2)
			board.move(Markers.A, index)
			
			board.posIs(Markers.A, index) and
				! board.posIs(Markers.B, index)
		}
		
		"have available moves" in {
			board.hasMovesLeft must beEqualTo(true)
		}

		"detect when there are no moves remaining" in {
			board.getPositionIndices().foreach(i => {
				board.move(Markers.A, i)
			})
			board.hasMovesLeft must beEqualTo(false)
		}

    }
	
}
