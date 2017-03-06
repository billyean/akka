package learningScala

/**
  * Created by hyan on 3/6/17.
  */
object Workday extends App{
  def reverse[T](seq: Seq[T]): Seq[T] =  {
    seq match {
      case Nil => Seq()
      case head :: tail => reverse(tail) :+ head
    }
  }



  def firstNonDup(s: String): Char = {
    val map = s.toCharArray.foldLeft(Map[Char, Int]())((map: Map[Char, Int], c: Char) =>  {
      var m = map
      if (m.contains(c)) {
        m += (c -> m.get(c).get.+(1))
      } else {
        m += (c -> 1)
      }

      m
    })

    val a = s.toCharArray.filter(c => map.get(c).getOrElse(0) == 1)

    a match {
      case Array() => ' '
      case _ => a(0)
    }
  }

  println(reverse(List('A', 'B', 'C', 'D', 'E')))
  println(firstNonDup("hello helen"))
}
