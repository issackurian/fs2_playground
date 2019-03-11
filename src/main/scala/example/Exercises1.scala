package example

import fs2._

import scala.util.Try

object Exercises1 {

  def repeat[F[_], O](s: Stream[F, O]): Stream[F, O] = {
    lazy val repeat0: Stream[F, O] = s ++ repeat0

    s ++ repeat0
  }

  def drain[F[_], O](s: Stream[F, O]): Stream[F, Nothing] = {
    s.flatMap(_ => Stream())
  }

  def eval_[F[_], O](fa: F[O]): Stream[F, Nothing] = {
    drain(Stream.eval(fa))
  }

  def attempt[F[_], O](s: Stream[F, O]): Stream[F, Either[Throwable, O]] = {
    s.map(Right(_): Either[Throwable, O])handleErrorWith(e => Stream(Left(e)))
  }
}
