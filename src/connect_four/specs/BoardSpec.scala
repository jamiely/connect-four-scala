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
	
	"should be able to test for bounded indices" in {
	  var board = new Board
	  def bounded(r: Int, c: Int) = board.isInBounds(new Index(r, c))
	  bounded(0, 0) must be(true)
	  bounded(6, 0) must be(false)
	  bounded(0, 7) must be(false)
	  bounded(-1, 0) must be(false)
	  bounded(0, -1) must be (false)
	  bounded(3,3) must be(true)
	}
	
	"should be able to convert index objects to array indices" in {
	  var board = new Board
	  def convert(r: Int, c: Int) = board.fromIndex(new Index(r, c))
	  
	  convert(0, 0) must beSome(0)
	  convert(0, 3) must beSome(3)
	  convert(0, 6) must beSome(6)
	  convert(1, 0) must beSome(7)
	  convert(1, 1) must beSome(8)
	  convert(2, 0) must beSome(14)
	  convert(2, 1) must beSome(15)
	  convert(-1, 0) must beNone
	}
	
	"should return true for isEmpty upon creation" in {
	  new Board().isEmpty() must be(true)
	}
}
