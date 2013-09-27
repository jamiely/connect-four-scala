package connect_four.specs

import ly.jamie.games.connect_four._
import org.specs2.mutable._

class GameOverSpec extends Specification {
	"a gameover check" should {
	  isolated
	  val untouchedGame = new Game
	  val game = new Game
	  
	  // setup a board that looks like 
      // ['', '', '', '', '', '', ''],
      // ['', '', '', '', '', '', ''],
      // ['', '', 'a', '', '', '', ''],
      // ['', '', 'a', '', '', '', ''],
      // ['', '', 'a', '', '', '', ''],
      // ['', '', 'a', '', '', '', '']

	  val verticalLineBoard = game.board
	  def vert(row: Int) = {
	    verticalLineBoard.move(Markers.A, new Index(row,2))
	  }
	  vert(0)
	  vert(1)
	  vert(2)
	  vert(3)
	  
	  "return true when checkPosition is called with 0 steps" in {
		  game.checkPosition(new Index(0,0), Markers.A, (0,0), 0) and
		  	  game.checkPosition(new Index(2,5), Markers.B, (2,2), 0)
	  }
	  
	  "return true when checkPosition is called with an index and the marker at that index and 1 step" in {
		  val index = new Index(2,2)
		  // setup board
		  game.checkPosition(index, Markers.A, (1,0), 1)
	  }
	  
	  "return true when checkPosition is called with an index and the marker at that index and 2 steps" in {
		  val index = new Index(2,2)
		  // setup board
		  game.checkPosition(index, Markers.A, (1,0), 2)
	  }
	  
	  "can check whether there are markers in a row" in {
		  val index = new Index(0,2)
		  // setup board
		  game.checkPosition(index, Markers.A, (1,0), 4)
	  }
	  
	  "it shouldn't be a player's win at the beginning" in {
		  untouchedGame.isWin must beEqualTo(false)
	  }
	  
	  "it shouldn't be a player's win at using the vertical line board" in {
		  game.isWin must beEqualTo(true)
	  }
	}
}
