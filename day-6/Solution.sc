import scala.io.Source
import scala.util.Using

def sum(op: (Set[String], Set[String]) => Set[String]) =
  Using(Source.fromFile("input.txt")) {
    _.mkString
     .split("\n{2}")
     .map(_.split("\n").map(_.split("").toSet).reduce(op).size)
     .sum
  }

sum(_.union(_)).foreach(println)
sum(_.intersect(_)).foreach(println)
