import scala.annotation.tailrec
import scala.io.Source
import scala.util.Using

@tailrec
def count(acc: Int, pos: Int, grid: Seq[String]): Int = {
  if (grid.isEmpty) {
    return acc
  }

  val line = grid.head
  val newPos = (pos + 3) % line.size

  count(if (line(newPos) == '#') acc + 1 else acc, newPos, grid.tail)
}

Using(Source.fromFile("input.txt")) {
  _.getLines()
   .toSeq
}
.map(grid => count(0, 0, grid.tail))
.foreach(println)
