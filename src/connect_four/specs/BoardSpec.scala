package connect_four.specs

import connect_four._
import org.specs._

class BoardSpec extends Specification {
	"Board has size with height of 6 and width 7" in {
	  val board = new Board
	  board.size.width must be equalTo(7)
	  board.size.height must be equalTo(6)
	}
	
	"All of the board spaces should be empty" in {
	  val board = new Board
	  val allEmpty = board.board.forall(marker => marker == Markers.Empty)
	  
	  allEmpty must be(true)
	}
	
	"should have appropriate position indices" in {
	  val board = new Board
	  val example = List((0, 0), (0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (2, 0), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (3, 0), (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (4, 0), (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (5, 0), (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6))
	  val indices = board.getPositionIndices().map(_.tuple)
	  
	  indices must containAll(example)
	}
}
