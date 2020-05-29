package simple

trait Plus[A] {
  def plus(a: A): A

  def plus(a1: A, a2: A): A
}


object Polymorphism {
  def head[A](xs: List[A]) : A = xs(0)

  def plus[A <: Plus[A]](a1: A, a2: A) = a1.plus(a2)

  def plus1[A : Plus](a1: A, a2: A) = implicitly[Plus[A]].plus(a1, a2)

  val x = head(1 :: 2 :: Nil)
}
