import scala.util.Using
import scala.io.Source

case class Row(x: Int, y: Int, char: Char, password: String)

def count(filename: String)(predicate: Row => Boolean) =
  Using(Source.fromFile(filename)) {
    _.getLines()
     .map { line =>
       val s"$from-$to $char: $password" = line
       Row(from.toInt, to.toInt, char.head, password)
     }
     .count(predicate)
  }

count("input.txt") { case (x, y, char, password) => (x to y).contains(password.count(_ == char)) } 
  .foreach(println)

count("input.txt") { case (x, y, char, password) => password(x - 1) == char ^ password(y - 1) == char }
  .foreach(println)
