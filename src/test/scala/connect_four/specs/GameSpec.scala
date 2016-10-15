package connect_four.specs

import ly.jamie.games.connect_four._
import org.specs2.mutable._

class GameSpec extends Specification {
  "a new game" should {
    isolated
    val game = new Game

    "have a board" in {
      game.board must haveClass[Board]
    }
    "have directions" in {
      game.directions must be equalTo (List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)))
    }
  }
}
