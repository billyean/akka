package funsets

import common._

/**
 * 2. Purely Functional Sets.
 */
object FunSets {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type Set = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
   */
  def singletonSet(elem: Int): Set = {
    def isSame(check: Int): Boolean = {
      check == elem
    }   
    isSame
  }

  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
  def union(s: Set, t: Set): Set = {
    def unionSet(elem: Int): Boolean = {
      if(!s(elem)) t(elem) else true
    }
    unionSet
  }

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
  def intersect(s: Set, t: Set): Set = {
    def intersectSet(check: Int): Boolean = {
      if(contains(s, check)) contains(t, check) else false
    }
    intersectSet
  }

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: Set, t: Set): Set = {
    def diffSet(elem: Int): Boolean = {
      if(s(elem))  !t(elem) else false
    }
    diffSet
  }

  /**
   * Returns the subset of `s` for which `p` holds.
   */
  def filter(s: Set, p: Int => Boolean): Set = {
    def filterSet(elem: Int): Boolean = {
      if(contains(s, elem)) p(elem) else false
    }
    filterSet
  }

  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a == 1000) s(a) && p(a) || !s(a)
      else if (s(a) && !p(a)) false
      else iter(a+1)
    }
    iter(-1000)
  }

  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
  def exists(s: Set, p: Int => Boolean): Boolean = {
     def not(f: Int => Boolean): Int => Boolean = {
         def notf(v: Int): Boolean = !p(v)
         notf 
     }
     !forall(s, not(p))
  }

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
  def map(s: Set, f: Int => Int): Set = {
    def mapSet(elem: Int): Boolean = {
      def equalF(check: Int): Boolean = {
        f(check) == elem
      }

      exists(s, equalF)
    }
    mapSet 
  }

  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}
