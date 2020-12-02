import scala.util.Using
import scala.io.Source

case class Row(x: Int, y: Int, char: Char, password: String)

def count(filename: String)(predicate: Row => Boolean) =
  Using(Source.fromFile(filename)) {
    _.getLines()
     .map { line =>
       val s"$x-$y $char: $password" = line
       Row(x.toInt, y.toInt, char.head, password)
     }
     .count(predicate)
  }

count("input.txt")(row => (row.x to row.y).contains(row.password.count(_ == row.char)))
  .foreach(println)

count("input.txt")(row => row.password(row.x - 1) == row.char ^ row.password(row.y - 1) == row.char)
  .foreach(println)
