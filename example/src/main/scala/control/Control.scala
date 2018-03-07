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

  def reversePrint(n: Int): Unit = {
    for (i <- (0 to n + 1).reverse) {
      println(i)
    }
  }

  /**
    * Computing product of the unicode codes of all letters in a string
    * @param string a string to compute product of the unicode codes
    * @return product of the unicode codes of all letters by given string
    */
  def product(string: String ): Int = {
    var p = 1

    for (c <- string) {
      println(c.toInt)
      p *= c.toInt
    }

    p
  }

  /**
    * Computing product of the unicode codes of all letters in a string
    * by using map(WIthout a loop）
    * @param string a string to compute product of the unicode codes
    * @return product of the unicode codes of all letters by given string
    */
  def mapProduct(string: String ): Int = {
    string.map(_.toInt).foldLeft(1)((base: Int, c: Int) => base * c)
  }

  /**
    * Computing product of the unicode codes of all letters in a string
    * by a recursive call.
    * @param string a string to compute product of the unicode codes
    * @return product of the unicode codes of all letters by given string
    */
  def recursiveProduct(string: String): Int = {
    def inner(string: List[Char]): Int = {
      string match {
        case List() => return 1
        case c :: follow => {
          return c.toInt * inner(follow)
        }
      }
    }

    inner(string.toList)
  }

  /**
    * A power function to take below way to calculate
    * x ^ n  = y ^ 2 if n is even and positive, where y = x ^ (n / 2)
    * x ^ n = x ^ (n - 1) * n if n is odd and positive
    * x ^ 0 = 1
    * x ^ n = 1 / x ^ (-n) if n is negative
    * * @param x
    * @param n
    * @return
    */
  def power(x: Int, n: Int): BigDecimal = {
    n match {
      case 0 => 1
      case z if z < 0 => 1 / power(x, -z)
      case z if z / 2 * 2 == z => {
        val h = power(x, z / 2)
        h * h
      }
      case _ => x * power(x, n - 1)
    }
  }
}
