package ly.jamie.games.connect_four

case class Move(col: Int)

class Game {
  var board = Board.default
  var currentMarker = Markers.A

  // directions are deltas used to check board locations in the cardinal directions
  val directions = (for {
    i <- -1 to 1
    j <- -1 to 1
    if !(i == 0 && j == 0)
  } yield (i, j)).toList

  /**
   * From the passed index, checks whether the position in the passed direction/delta
   * contains the passed marker, and does this `steps` in that direction.
   */
  def checkPosition(index: Index, marker: Markers.Marker,
    delta: (Int, Int), steps: Int): Boolean =
    if (steps == 0) true
    else if (board.isInBounds(index) && board.posIs(marker, index)) {
      checkPosition(
        Index(index.row + delta._1, index.col + delta._2),
        marker,
        delta,
        steps - 1
      )
    } else false

  def isWin: Boolean = board.getPositionIndices().exists(testWinAtIndex)

  def testWinAtIndex(index: Index): Boolean = board.markerAt(index) match {
    case None => false
    case Some(Markers.Empty) => false
    case Some(m) => directions.exists(delta => checkPosition(index, m, delta, 4))
  }

  def getFirstEmptyRowInColumn(c: Int): Option[Int] = {
    def helper(row: Int): Int =
      if (row < 0) row
      else if (markerAt(new Index(row, c)) == Some(Markers.Empty)) row
      else helper(row - 1)

    if (!board.isInBounds(new Index(0, c))) None
    else {
      val r = helper(board.size.height - 1)
      if (r < 0) None
      else Some(r)
    }
  }

  def toggleMarker(): Markers.Marker = {
    currentMarker = if (currentMarker == Markers.A) Markers.B else Markers.A
    currentMarker
  }

  def updateBoard(index: Index): Markers.Marker = {
    var marker = currentMarker
    board = board.move(currentMarker, index) match {
      case Some(BoardUpdate(newBoard, _, _)) => {
        marker = toggleMarker()
        newBoard
      }
      case _ => board
    }
    marker
  }

  def move(mv: Move): Option[Markers.Marker] =
    if (isWin) None
    else for {
      row <- getFirstEmptyRowInColumn(mv.col)
      result <- if (row >= 0) Some(updateBoard(Index(row, mv.col)))
      else None
    } yield result

  def markerAt(index: Index): Option[Markers.Marker] = board.markerAt(index)

  def getCurrentMarker: Markers.Marker = currentMarker

}
