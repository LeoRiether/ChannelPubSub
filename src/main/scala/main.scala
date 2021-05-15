package main

import messages._
import java.nio.file.Path

object Main {

    def main(args: Array[String]) = {
        val ps = new PubSub
        val ds = new DataStorage(ps)
        val swf = new StopWordFilter(ps)
        val ws = new WordSplitter(ps)
        val wfc = new WordFrequencyCounter(ps)

        ps.message >> (m => println(s"> ${m}"))

        ps.wordCountResult >> printResult(20)

        val path = new java.io.File(".").getCanonicalPath + "/src/main/scala/bigtext.txt"
        ps.loadFile << LoadFile(path)
    }

    def printResult(n: Int)(w: WordCountResult) {
        println("----------------------------------------")

        w.result
            .toList
            .sortBy(x => (-x._2, x._1)) // sort by word count
            .take(n)
            .foreach(wc => println(s"${wc._1} - ${wc._2}"))

        println("----------------------------------------")
    }
}
