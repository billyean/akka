package simple

trait Monoid[A] {
  def mappend(a: A, b: A): A

  def mzero: A
}

object IntMonoid extends Monoid[Int] {
  def mappend(a: Int, b: Int): Int = a + b

  def mzero: Int = 0
}

object Sum extends App{
  def sum(xs: List[Int]): Int = xs.foldRight(0){_ + _}

  def sum1(xs: List[Int]): Int = xs.foldRight(IntMonoid.mzero){IntMonoid.mappend}

  def sum2[A](xs: List[A], m: Monoid[A]): A = xs.foldRight(m.mzero){m.mappend}

  println(s"sum(List(1,2,3,4)) = ${sum(List(1,2,3,4))}")
  println(s"sum1(List(1,2,3,4)) = ${sum1(List(1,2,3,4))}")
  println(s"sum1(List(1,2,3,4), IntMonoid) = ${sum2(List(1,2,3,4), IntMonoid)}")
}
