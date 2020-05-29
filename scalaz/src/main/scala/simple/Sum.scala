package simple

object Sum extends App{
  def sum(xs: List[Int]): Int = xs.foldRight(0){_ + _}

  println(s"sum(List(1,2,3,4)) = ${sum(List(1,2,3,4))}")
}
