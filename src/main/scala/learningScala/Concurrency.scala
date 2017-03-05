package learningScala

/**
  *
  */
object Concurrency extends App{
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

  val g : Int => Int = x => x + 10
  val f : Int => String = x => String.valueOf(x)
  println(compose(f, g)(20))


  println(check(0 until 10)(40 / _ > 0))
  println(check(1 until 10)(40 / _ > 0))


}
