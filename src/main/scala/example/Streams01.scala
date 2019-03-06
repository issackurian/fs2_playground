package example

object Streams01 extends App {

  import fs2.Stream

  val s0 = Stream.empty
  val s1 = Stream.emit(1)

  val s1a = Stream(1,2,3) // variadic

  val s1b = Stream.emits(List(1,2,3)) // accepts any Seq

}
