package connect_four.specs

import connect_four._
import org.specs2.mutable.Specification

class GamePlaySpec extends Specification {
	"game play" should {
	  isolated
	  val game = new Game
	  "happen in moves by choosing a column 1" in {
	    val c = 1
	    val move = new Move(c)
	    game.move(move)
	    val result = for {
	      result <- game.markerAt(new Index(5, c))
	    } yield result
	    result must beSome(Markers.A)
	  }
	  "happen in moves by choosing a column 2" in {
	    val c = 1
	    val move = new Move(c)
	    game.move(move)
	    game.move(move)
	    val result = for {
	      result <- game.markerAt(new Index(4, c))
	    } yield result
	    result must beSome(Markers.B)
	  }
	  
	  "alternate between two markers 1" in {
	    game.getCurrentMarker() must beEqualTo(Markers.A)
	  }
	  
	  "alternate between two markers 2" in {
	    game.move(new Move(1))
	    game.getCurrentMarker() must beEqualTo(Markers.B)
	  }
	  
	  "detect a win when someone gets a vertical match" in {
	    val a = new Move(1)
	    val b = new Move(2)
	    
	    List.range(1, 4).foreach(_ => {
	    	game.move(a)
	    	game.move(b)
	    })
	    
	    game.move(a)
	    game.isWin()
	  }
	  
	  "not allow a move to be made in a full column" in {
	    val move = new Move(1)
	    List.range(1, game.board.size.height+1).foreach(_ =>
	      game.move(move))
	    game.move(move) must beNone
	  }
	  
	  "determine first empty row in a given column" in {
	    game.move(new Move(1))
	    game.getFirstEmptyRowInColumn(1) must beSome(4)
	  }
	}
}