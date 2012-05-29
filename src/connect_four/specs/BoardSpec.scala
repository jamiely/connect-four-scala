package connect_four.specs

import connect_four._
import org.specs._

class BoardSpec extends Specification {
	"Board has size with height of 6 and width 7 and length of 42" in {
	  val board = new Board
	  board.size.width must be equalTo(7)
	  board.size.height must be equalTo(6)
	  board.length must be equalTo(42)
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
	  convert(5, 6) must beSome(41)
	  convert(-1, 0) must beNone
	}
	
	"should return true for isEmpty upon creation" in {
	  new Board().isEmpty() must be(true)
	}
	
	"should return marker when updatePosition called" in {
	  val board = new Board
	  board.updatePosition(Markers.A, 0) must be equalTo((Markers.A, 0))
	  board.updatePosition(Markers.A, 41) must be equalTo((Markers.A, 41))
	}
	
	"should return true when move is valid" in {
	  val board = new Board()
	  val index = new Index(0, 2)
	  board.move(Markers.A, index) must be(true)
	}
	"should return appropriate marker after move" in {
	  val board = new Board()
	  val index = new Index(0, 2)
	  board.move(Markers.A, index)
	  
	  board.markerAt(index) must beSome(Markers.A)
	}
	"should return appropriate marker after move 2" in {
	  val board = new Board()
	  val index = new Index(5, 6)
	  board.move(Markers.A, index)
	  
	  board.markerAt(index) must beSome(Markers.A)
	}	
	"should return posIs true when appropriate" in {
	  val board = new Board()
	  val index = new Index(0, 2)
	  board.move(Markers.A, index)
	  
	  board.posIs(Markers.A, index) must be(true)
	  board.posIs(Markers.B, index) must be(false)
	}
	
	"it should be possible to detect when there are no moves remaining" in {
	  val board = new Board
	  board.hasMovesLeft() must be(true)
	  board.getPositionIndices().foreach(i => {
	      //println(board.fromIndex(i))
		  board.move(Markers.A, i)
	  })
	  board.hasMovesLeft() must be(false)
	}
}
