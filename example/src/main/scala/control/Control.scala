package control

/**
  * Scala Impatient Exercise
  * Chapter 2
  */
object Control {
  /**
    * signum of a number is 1 if the number is positive, -1 if it's negative.
    * and 0 if it's zero.
    * @param number number to be calculated its signum
    * @return signum of given number
    */
  def signum(number: Int): Int = {
    number match {
      case 0 => return 0
      case x if x > 0 => return 1
      case x if x < 0 => return -1
    }
  }
}
