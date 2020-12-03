import scala.annotation.tailrec
import scala.io.Source
import scala.util.Using

case class Position(y: Int, x: Int)
case class Slope(right: Int, down: Int)

@tailrec
def count(acc: Long, pos: Position, slope: Slope, grid: Seq[String]): Long = {
  val newPos = Position(pos.y + slope.down, (pos.x + slope.right) % grid.head.size)

  if (newPos.y >= grid.size) acc else count(if (grid(newPos.y)(newPos.x) == '#') acc + 1 else acc, newPos, slope, grid)
}

def countTrees(filename: String, slope: Slope) =
  Using(Source.fromFile(filename)) {
    _.getLines()
     .toSeq
  }
  .map(count(0L, Position(0, 0), slope, _))

countTrees("input.txt", Slope(3, 1))
  .foreach(println)

var result = for {
  a <- countTrees("input.txt", Slope(1, 1))
  b <- countTrees("input.txt", Slope(3, 1))
  c <- countTrees("input.txt", Slope(5, 1))
  d <- countTrees("input.txt", Slope(7, 1))
  e <- countTrees("input.txt", Slope(1, 2))
} yield a * b * c * d * e

result.foreach(println)
