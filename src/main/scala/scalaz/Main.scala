package scalaz

import javax.swing.JFrame
import javax.swing.WindowConstants.DISPOSE_ON_CLOSE

object Main {
  def main(args: Array[String]) {
    println("scalaz-depend")
    val j = new JFrame("test")
    j setDefaultCloseOperation DISPOSE_ON_CLOSE
    j setVisible true
  }

  val f: Int => String = _ => "abc"
}
