package example

import fs2._

object Exercises2 {

  def tk[F[_],O](n: Long): Pipe[F,O,O] = {
    def go(s: Stream[F,O], n: Long): Pull[F,O,Unit] = {
      s.pull.uncons.flatMap {
        case Some((hd,tl)) =>
          hd.size match {
            case m if m <= n => Pull.output(hd) >> go(tl, n - m)
            case m => Pull.output(hd.take(n.toInt)) >> Pull.done
          }
        case None => Pull.done
      }
    }
    in => go(in,n).stream
  }
  // tk: [F[_], O](n: Long)fs2.Pipe[F,O,O]

  def tk2[F[_],O](n: Long): Pipe[F,O,O] =
    in => in.scanChunksOpt(n) { n =>
      if (n <= 0) None
      else Some(c => c.size match {
        case m if m < n => (n - m, c)
        case m => (0, c.take(n.toInt))
      })
    }
  // tk: [F[_], O](n: Long)fs2.Pipe[F,O,O]


  //  Stream(1,2,3,4).through(tk(2)).toList
  // res32: List[Int] = List(1, 2)

  def tkWhile[F[_],O](predicate: O => Boolean): Pipe[F,O,O] = {
    def go(s: Stream[F,O], predicate: O => Boolean): Pull[F,O,Unit] = {
      s.pull.uncons.flatMap {
        case Some((hd, tl)) =>
          hd.indexWhere(x => !predicate(x)) match {
            case Some(n) => Pull.output(hd.take(n)) >> Pull.done
            case None => Pull.output(hd) >> go(tl, predicate)
          }
        case None => Pull.done
      }
    }

    in => go(in, predicate).stream
  }

  def tkWhile2[F[_],O](predicate: O => Boolean): Pipe[F,O,O] =
    in => in.scanChunksOpt(false) { s =>
      if (s) None
      else Some(c => c.indexWhere(x => !predicate(x)) match {
        case Some(n) => (true, c.take(n))
        case None => (false, c)
      })
    }

  def intersperse[F[_],O](item: O): Pipe[F,O,O] = {
    def go(s: Stream[F,O], item: O): Pull[F,O,Unit] = {
      s.pull.uncons.flatMap {
        case Some((hd, tl)) =>
          val tmpPull: Pull[F,O,Unit] = Pull.output(hd.flatMap(x => Chunk(x, item)))
          tmpPull >> go(tl, item)
        case None => Pull.done
      }
    }

    in => go(in, item).stream
  }
}
