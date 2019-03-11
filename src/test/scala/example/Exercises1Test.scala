package example

import org.scalatest.FlatSpec
import fs2._

class Exercises1Test extends FlatSpec {

  import Exercises1._

  behavior of "Exercises1Test"

  it should "repeat" in {
    val infiniteStream = Exercises1.repeat(Stream(1,2,3))

    assertResult(infiniteStream.take(100).toList.size)(100)
  }

  it should "drain the Stream" in {
    val stream = Stream.range(1, 100)

    assertResult(stream.drain.toList)(drain(stream).toList)
  }

  it should "attempt to evaluate the stream " in {
    val exception = new RuntimeException("Oh noes!!!")
    val stream = (Stream.range(1, 5) ++ (throw exception))

    assertResult(stream.attempt.toList)(attempt(stream).toList)
  }

}
