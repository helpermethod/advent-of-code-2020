import $file.funs

val count = funs.count("input.txt") { row =>
  (row.x to row.y).contains(row.password.count(_ == row.char))
}

count.foreach(println)
