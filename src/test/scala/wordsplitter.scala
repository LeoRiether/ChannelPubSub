import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import eventstream._
import main.{WordSplitter}

object WordSplitterSpec extends Properties("WordSplitter") {
    val ps = new PubSub

    val ws = new WordSplitter(ps)

    property("") = forAll { (s: String) =>
    }
}