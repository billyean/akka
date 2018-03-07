package array

import scala.util.Random

object WorkingWithArray {
  /**
    * Get a array that set n random integers between 0 (inclusive)
    * and n(exclusive)
    * @param a
    * @param n
    * @return
    */
  def setRandomN(a: Array[Int], n: Int) {
      for (index <- 0 until a.length) {
        a(index) = Random.nextInt(n)
      }
  }

  /**
    * Swap adjacent elements of an array of integers
    * @param array integer array
    */
  def swapAdjacent(array: Array[Int]) {
    for (index <- 0 until array.length / 2) {
      val s = array(2 * index)
      array(2 * index) = array(2 * index + 1)
      array(2 * index + 1) = s
    }
  }

  /**
    * Swap adjacent elements of an array of integers
    * by creating a extra collection
    * @param array integer array
    */
  def yieldAdjacent(array: Array[Int]): Seq[Int] = {
    for {i <- 0 until array.length;
         index = i match {
           case z if z % 2 == 0 && z == array.length - 1 => z
           case z if z % 2 == 0 => z + 1
           case _ => i - 1
         }
    } yield array(index)
  }

  /**
    * Produce a new array that contains all positive values of original array,
    * in the original order, followed by all values that are zero or negative,
    * in their original order
    * @param array
    * @return
    */
  def seperatePositive(array: Array[Int]): Array[Int] = {
    var positive : Array[Int] = for (elem <- array if elem > 0) yield elem
    val other : Array[Int] = for (elem <- array if elem <= 0) yield elem
    positive ++ other
  }

  /**
    * Compute average of doubles
    * @param array
    * @return
    */
  def average(array: Array[Double]): Double = {
    array.sum / array.length
  }

  /**
    * Reverse an array
    * @param array
    * @return
    */
  def reverse(array: Array[Int]): Array[Int] = {
    array.reverse
  }

  def removeDuplicate(array: Array[Int]): Array[Int] = {
    array.distinct
  }
}
