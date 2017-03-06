package recfun
import common._

object Main {
  def main(args: Array[String]) {
//    println("Pascal's Triangle")
//    for (row <- 0 to 10) {
//      for (col <- 0 to row)
//        print(pascal(col, row) + " ")
//      println()
//    }
//  
//    print(countChange(4,List(1,2)))
    print(countChange(300,List(5,10,20,50,100,200,500)))
    //print(countChange(301,List(5,10,20,50,100,200,500)))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = 
    if (c == 0) 1 else if (c == r) 1 else pascal(c - 1, r - 1) + pascal(c, r-1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def testBalance(parens: Int, nextChars : List[Char]): Int = {
      if(nextChars.isEmpty) parens
      else if(nextChars.head == '(') testBalance(parens + 1, nextChars.tail)
      else if(nextChars.head != ')') testBalance(parens, nextChars.tail)
      else if(parens == 0) -1
      else testBalance(parens - 1, nextChars.tail)   
    }
    
    testBalance(0, chars) == 0;
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def loop(acc : Int, left : Int, inner: List[Int]): Int = {
      if (inner.isEmpty) acc
      else if (left < inner.head) acc
      else loop(countInnerChange(acc, left - inner.head, inner), left, inner.tail)
    }

    def countInnerChange(acc : Int, moneyCal: Int, calCoins: List[Int]): Int = {
      if (moneyCal < 0)  acc
      else if(moneyCal == 0) acc + 1
      else loop(acc, moneyCal, calCoins)
    }
    
    countInnerChange(0, money, coins.sorted)
  }
}
