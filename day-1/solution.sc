import scala.util.Using
import scala.io.Source

@main
def main(n: Int) {
  Using(Source.fromFile("input.txt")) {
    _.getLines()
     .toSeq
     .map(_.toInt)
     .combinations(n)
     .find(_.sum == 2020)
     .map(_.product)
     .foreach(println)
  }
}
