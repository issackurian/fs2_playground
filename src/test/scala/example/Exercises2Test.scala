package example

import org.scalatest.FlatSpec
import fs2._
import Exercises2._

class Exercises2Test extends FlatSpec {

  "tk" should "take the 1st x items" in {
    assertResult(Stream.range(1, 10).through(tk(5)).toList)(1 to 5 toList)
  }

  "tk2" should "take the 1st x items" in {
    assertResult(Stream.range(1, 10).through(tk2(5)).toList)(1 to 5 toList)
  }


  "tkWhile" should "take all items until the predicate is true" in {
    assertResult(Stream.range(1, 10).through(tkWhile(_ < 10)).toList)(1 to 9 toList)
  }

  "tkWhile2" should "take all items until the predicate is true" in {
    assertResult(Stream.range(1, 10).through(tkWhile2(_ < 10)).toList)(1 to 9 toList)
  }

  "intersperse" should "take intersperse the item in between elements" in {
    assertResult(Stream.range(1, 10).through(intersperse(999)).through(tk(5)).toList)(List(1,999,2,999,3))
  }

  "intersperse2" should "take intersperse the item in between elements" in {
    assertResult(Stream.range(1, 10).through(intersperse2(999)).through(tk(5)).toList)(List(1,999,2,999,3))
  }
}
