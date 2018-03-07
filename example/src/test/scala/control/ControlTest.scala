package control

import org.scalatest.{FunSuite, Matchers}

class ControlTest extends FunSuite with Matchers {
  test("signum positive") {
    assert(Control.signum(10) === 1)
  }

  test("signum negative") {
    assert(Control.signum(-5) === -1)
  }

  test("signum zero") {
    assert(Control.signum(0) === 0)
  }

  /**
    * Scala Impatient Chapter 2, Exercise 2
    */
  test("empty block type is Unit") {
    assert({}.isInstanceOf[Unit])
  }

  /**
    * Scala Impatient Chapter 2, Exercise 4
    */
  test("reverse print") {
    Control.reversePrint(10)
  }

  test("String unicode product") {
    assert(Control.product("Hello") === 825152896)
  }

  test("Empty String unicode product") {
    assert(Control.product("") === 1)
  }

  test("String unicode map product") {
    assert(Control.mapProduct("Hello") === 825152896)
  }

  test("Empty String unicode map product") {
    assert(Control.mapProduct("") === 1)
  }


  test("String unicode recursive product") {
    assert(Control.recursiveProduct("Hello") === 825152896)
  }

  test("Empty String unicode recursive product") {
    assert(Control.recursiveProduct("") === 1)
  }

  test("3 ^ 1 == 1") {
    assert(Control.power(3, 1) === 3)
  }

  test("3 ^ 2 == 1") {
    assert(Control.power(3, 2) === 9)
  }

  test("3 ^ 5 == 243") {
    assert(Control.power(3, 5) === 243)
  }

  test("3 ^ 4 == 81") {
    assert(Control.power(3, 4) === 81)
  }

  test("2 ^ -1 == 0.5") {
    assert(Control.power(2, -1) === 0.5)
  }

  test("3 ^ 0 == 1") {
    assert(Control.power(3, 0) === 1)
  }
}
