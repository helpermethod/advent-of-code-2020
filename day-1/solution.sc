import scala.util.Using
import scala.io.Source

def printProduct(n: Int): Unit =
  Using(Source.fromFile("input.txt")) {
     _.getLines()
      .toSeq
      .map(_.toInt)
      .combinations(n)
      .find(_.sum == 2020)
      .map(_.product)
      .foreach(println)
  }

@main
def main(n: Int = 2) {
  printProduct(n)
}
