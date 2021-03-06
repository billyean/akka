package learningScala

import java.util.stream.Collectors

import scala.annotation.tailrec

/**
  *
  */
object Basic extends App{
  /**
    * Implement a compose  method with the following signature:
    * @param g
    * @param f
    * @tparam A
    * @tparam B
    * @tparam C
    * @return a function h, which is the composition of the functions f and g
    */
  def compose[A, B, C](g : B => C, f : A => B) : A => C = x => g(f(x))

  /**
    * The resulting Option object should contain a tuple of values from the Option objects a and b, given that both a
    * and b are non-empty. Use for-comprehensions
    * @param a
    * @param b
    * @tparam A
    * @tparam B
    * @return
    */
  def fuse[A,B] (a: Option[A], b: Option[B]): Option[(A, B)] = {
    (a, b) match {
      case (None, _) => return None
      case (_, None) => return None
      case _ => return Some((a.get, b.get))
    }
  }

  /**
    * Implement a check method, which takes a set of values of type T and a function of type T => Boolean: The method
    * must return true if and only if the pred function returns true for all the values in xs without throwing an
    * exception. Use the check method as follows:
    * check(0 until 10)(40 / _ > 0)
    * @param xs
    * @param pred
    * @tparam T
    * @return
    */
  def check[T](xs: Seq[T]) (pred: T => Boolean) : Boolean = {
    xs.forall(x =>
      try {
        pred(x)
      } catch {
        case e: Exception => false
      }

    )
  }

  /**
    * Given a string, returns a sequence of strings that are lexicographic permutations of the input string:
    * @param x
    * @return
    */
  def permutation(x: String): Seq[String] = {
    def innerPermutation(seq: Seq[String], string: String): Seq[String] = {
      (string.length, seq) match {
        case (0, _) => seq
        case (1, Seq()) => Seq(string)
        case _ => {
              val next = innerPermutation (seq, string.substring(1))
              val head = string.substring (0, 1)
              next.flatMap (s => (0 until s.length + 1)
                .map (i => s.substring (0, i).concat (head).concat (s.substring (i)))).distinct
        }
      }
    }

    innerPermutation(Seq(), x)
  }

  /**
    * Produces an iterator over all possible combinations of length n by given a sequence of elements. A combination is
    * a way of selecting elements from the collection so that every element is selected once, and the order of elements
    * does not matter.
    * For example, given a collection Seq(1, 4, 9, 16), combinations of length 2 are Seq(1, 4), Seq(1, 9), Seq(1, 16),
    * Seq(4, 9), Seq(4, 16), and Seq(9, 16)
    * @param n
    * @param xs
    * @return
    */
  def combinations(n: Int, xs: Seq[Int]): Iterator[Seq[Int]] = {
    def innerCombinations(result: Seq[Seq[Int]], n: Int, xs: Seq[Int]): Seq[Seq[Int]] = {
      if (n == xs.length) {
        result :+ xs
      } else if (n > xs.length) {
        result
      } else {
        (0 until xs.length).flatMap(i => innerCombinations(result, n, (xs.take(i)) ++ (xs.drop(i + 1)))).distinct
      }
    }

    innerCombinations(Seq(), n, xs).iterator
  }

  /**
    * A partial function from a string to lists of matches within that string by given regular expression.
    * @param regex
    * @return
    */
  def matcher (regex: String): PartialFunction[String, List[String]] = ???


  /**
    * A simple higher order function. Higher order function is a function that take a function as a parameter or return
    * functions. Here sum take a function f as variable. return a function that doing sum of on function f.
    * @param f
    * @return
    */
  def sum(f: Int => Int): (Seq[Int]) => Int = {
    (seq: Seq[Int]) => seq.map(s => f(s)).sum
  }


  val g : Int => Int = x => x + 10
  val f : Int => String = x => String.valueOf(x)
  println(compose(f, g)(20))


  println(check(0 until 10)(40 / _ > 0))
  println(check(1 until 10)(40 / _ > 0))

  val merge = (s1: String, s2: String) => {
    s1 match {
      case "" => s2
      case _ => s1.concat(", ").concat(s2)
    }
  }


  List("", "O", "four", "All", "abcdefghij").foreach(s => {
//    val sPermutated = permutation(s).foldLeft("")(merge)
    val sPermutated = permutation(s).mkString(",")
    println(s"""Permutation of "${s}" is [${sPermutated}]""")
  })


  Seq(combinations(2, Seq(1, 4, 9, 16)), combinations(2, Seq(1, 4, 4, 16)), combinations(8, Seq(1, 4, 9, 16))).foreach(iter => {
    println("============")
    iter.foreach(println)
  })

  println(sum(v => v * v)(List(1,2,3)))
  println(sum(v => v * v * v)(List(1,2,3, 4)))


  val sum1 : (Int => Int, Seq[Int]) => Int = (f, seq) => seq.map(s => f(s)).sum
  val sum1Curried = sum1.curried
  println(sum1Curried(v => 2 * v )(List(1,2,3,4,5,6)))

}
