package learningScala

object Collections extends App {
  // Array is mutable
  val numbers = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  for (index <- 0 until numbers.length)
    numbers(index) -= index * 2

  numbers.foreach(println)
}
