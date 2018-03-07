package array

import org.scalatest.{FunSuite, Matchers}

class WorkingWithArrayTest extends FunSuite with Matchers {
  test("set array random") {
    val array = Array(1, 5, 6, 8, 9)
    WorkingWithArray.setRandomN(array, 1)
    for (e <- array) {
      assert(e < 1)
    }
  }

  test("swap adjacent elements") {
    val array = Array(1, 2, 3, 4, 5)
    WorkingWithArray.swapAdjacent(array)
    assert(array === Array(2, 1, 4, 3, 5))
  }

  test("yield swap adjacent elements") {
    val array = Array(1, 2, 3, 4, 5)
    assert(WorkingWithArray.yieldAdjacent(array) === Array(2, 1, 4, 3, 5))
  }

  test("seperate all positive in ahead for only positive") {
    val array = Array(1, 2, 3, 4, 5)
    assert(WorkingWithArray.seperatePositive(array) === Array(1, 2, 3, 4, 5))
  }

  test("seperate all positive in ahead for only zero or negative") {
    val array = Array(-1, 0, -3, -4, -5)
    assert(WorkingWithArray.seperatePositive(array) === Array(-1, 0, -3, -4, -5))
  }

  test("seperate all positive in ahead for mixed") {
    val array = Array(-1, 7, 0, -3, 8, 10, -4, -5)
    assert(WorkingWithArray.seperatePositive(array) === Array(7, 8, 10, -1, 0, -3, -4, -5))
  }

  test("seperate all positive in ahead for mixed ordered") {
    val array = Array(7, 8, 10, -1, 0, -3, -4, -5)
    assert(WorkingWithArray.seperatePositive(array) === Array(7, 8, 10, -1, 0, -3, -4, -5))
  }

  test("reverse array") {
    val array = Array(7, 8, 10, -1, 0, -3, -4, -5)
    assert(WorkingWithArray.reverse(array) === Array(-5, -4, -3, 0, -1, 10, 8, 7))
  }

  test("remove duplicate array in no duplicate") {
    val array = Array(7, 8, 10, -1, 0, -3, -4, -5)
    assert(WorkingWithArray.removeDuplicate(array).sorted === Array(-5, -4, -3, 0, -1, 10, 8, 7).sorted)
  }

  test("remove duplicate array has duplictae") {
    val array = Array(7, 7, 8, 7, 8)
    assert(WorkingWithArray.removeDuplicate(array).sorted === Array(7, 8).sorted)
  }
}
