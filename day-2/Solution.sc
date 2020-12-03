import scala.io.Source
import scala.util.Using

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

count("input.txt") { case (x, y, char, password) => (x to y).contains(password.count(_ == char)) } 
  .foreach(println)

count("input.txt") { case (x, y, char, password) => password(x - 1) == char ^ password(y - 1) == char }
  .foreach(println)
