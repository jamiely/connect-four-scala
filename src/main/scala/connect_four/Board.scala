package ly.jamie.games.connect_four

object Markers extends Enumeration {
  type Marker = Value
  val Empty, A, B = Value
}

case class Index(row: Int, col: Int) {
  lazy val tuple: (Int, Int) = (row, col)
}

object Size {
  val default = Size(width = 7, height = 6) // scalastyle:ignore
}
case class Size(width: Int, height: Int)

case class BoardUpdate(board: Board, marker: Markers.Marker, position: Int)

object Board {
  def apply(size: Size): Board = Board(
    size,
    IndexedSeq.fill(size.width * size.height)(Markers.Empty)
  )
  lazy val default = Board(Size.default)
}
case class Board(size: Size, board: Seq[Markers.Marker]) {

  lazy val length = size.width * size.height

  // Return a list of index objects
  def getPositionIndices(): IndexedSeq[Index] =
    for {
      row <- 0 to size.height - 1
      col <- 0 to size.width - 1
    } yield new Index(row, col)

  def isEmpty: Boolean = board.forall(x => x == Markers.Empty)

  def isInBounds(index: Index): Boolean = index.row >= 0 &&
    index.row < size.height && index.col >= 0 && index.col < size.width

  def fromIndex(index: Index): Option[Int] =
    if (isInBounds(index)) {
      Some(index.row * size.width + index.col)
    } else {
      None
    }

  def move(marker: Markers.Marker, index: Index): Option[BoardUpdate] =
    fromIndex(index).map { updatePosition(marker, _) }

  // Updates the given position without performing a check.
  // @returns    Returns a pair of the marker that was put at the position and the position.
  def updatePosition(marker: Markers.Marker, position: Int): BoardUpdate =
    BoardUpdate(
      Board(size, board.updated(position, marker)),
      marker, position
    )

  def markerAt(index: Index): Option[Markers.Marker] =
    for {
      pos <- fromIndex(index)
      m <- Some(board(pos))
    } yield m

  def posIs(marker: Markers.Marker, index: Index): Boolean = {
    val result: Option[Boolean] = for {
      m <- markerAt(index)
      // return the result of a check against the marker
      result <- Some(m == marker)
    } yield result

    result.getOrElse(false)
  }

  def hasMovesLeft: Boolean = board.exists { _ == Markers.Empty }

}
