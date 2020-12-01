import scala.util.Using
import scala.io.Source

Using(Source.fromFile("input.txt")) {
   _.getLines()
    .toSeq
    .map(_.toInt)
    .combinations(2)
    .find(_.sum == 2020)
    .map(_.reduceLeft(_ * _))
    .foreach(println)
}
