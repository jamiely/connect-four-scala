package connect_four

class Game {
	val board = new Board
	val directions = for {
	  i <- List.range(-1, 2); 
	  j <- List.range(-1, 2);
	  if !(i==0 && j==0)
	  } yield (i, j)
}
