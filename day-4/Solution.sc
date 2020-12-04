import scala.util.Using
import scala.io.Source

case class Validator(key: String, check: String => Boolean)

val fieldsPresent = List(
  Validator("ecl", s => true),
  Validator("byr", s => true),
  Validator("eyr", s => true),
  Validator("iyr", s => true),
  Validator("hgt", s => true),
  Validator("hcl", s => true),
  Validator("pid", s => true)
)

val fieldsPresentAndValid = List(
  Validator("ecl", s => s.matches("amb blu brn gry grn hzl oth")),
  Validator("byr", s => (1920 to 2002).contains(s.toInt)),
  Validator("eyr", s => (2020 to 2030).contains(s.toInt)),
  Validator("iyr", s => (2010 to 2020).contains(s.toInt)),
  Validator("hgt", s => s.matches("#(?:[0-9]{6}|[a-d]{6})")),
  Validator("hcl", s => s.matches("(?:1[5-8][0-9]|9[0-3])cm|(?:59|6[0-9]|7[0-6]in)")),
  Validator("pid", s => s.matches("[0-9]{9}"))
)

def count(filename: String, validators: Seq[Validator]) =
  Using(Source.fromFile(filename)) {
    _.mkString("")
     .split("\n{2}")
     .map(_.split(" |\n").map(_.split(":")).map(field => field(0) -> field(1)).toMap)
     .count(passport => validators.foldLeft(true)((acc, validator) => acc && passport.contains(validator.key) && validator.check(passport(validator.key))))
  }

count("input.txt", fieldsPresent).foreach(println)
count("input.txt", fieldsPresentAndValid).foreach(println)
