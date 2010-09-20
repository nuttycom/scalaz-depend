package scalaz

import javax.swing.JFrame

object Main {
  def main(args: Array[String]) {
    println("scalaz-depend")
    new JFrame("test") setVisible true
  }

  val f: Int => String = _ => "abc"
}

