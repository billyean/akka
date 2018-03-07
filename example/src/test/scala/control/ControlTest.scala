package control

import org.scalatest.{FunSuite, Matchers}


class ControlTest extends FunSuite with Matchers {
  test("signum positive") {
    assert(Control.signum(10) == 1)
  }

  test("signum negative") {
    assert(Control.signum(-5) == -1)
  }

  test("signum zero") {
    assert(Control.signum(0) == 0)
  }
}
