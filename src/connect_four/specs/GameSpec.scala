package connect_four.specs

import connect_four._
import org.specs.Specification

class GameSpec extends Specification {
  "it should have a board" in {
    val game = new Game
    game.board must haveClass[Board]
  }
  "it should have directions" in {
    new Game().directions must be equalTo(List((-1,-1), (-1, 0), (-1, 1), (0,-1), (0, 1), (1,-1), (1, 0), (1, 1)))
  }
}
