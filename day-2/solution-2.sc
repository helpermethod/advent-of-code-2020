import $file.funs

val count = funs.count("input.txt") { row =>
  row.password(row.x - 1) == row.char ^ row.password(row.y - 1) == row.char
}

count.foreach(println)
