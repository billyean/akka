package learningScala

object Func extends App {
  // Create a curried function by using curried
  def sum(a: Int, b: Int, c: Int): Int = a + b + c

  val sumCurried = (sum _).curried

  val six = sumCurried(1)(2)(3)

  println(s"1 + 2 + 3 = $six")


  // def variable length arguments function
  def capitalizeAll(strings: String*) = {
    strings.map(s => s.capitalize)
  }

  capitalizeAll("hello", "world").foreach(print)

  // Here curried can't work with variable length arguments, below code is not working
  //  def mySum(values: Int*): Int = values.sum
  //
  //  val mysumCurried = (mySum _).curried //Can't resolve symbol curried
}
