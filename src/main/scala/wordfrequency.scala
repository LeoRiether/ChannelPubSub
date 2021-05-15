package main

import messages._

import scala.collection.mutable.Map

class WordFrequencyCounter(val ps: PubSub) {
    var freq = Map.empty[String, Int]

    ps.validWord >> incrementCount
    ps.endOfFile >> publishResult

    def incrementCount(w: ValidWord) =
        freq.updateWith(w.word) {
            case Some(x) => Some(x + 1)
            case None    => Some(1)
        }

    def publishResult(eof: EndOfFile) =
        ps.wordCountResult << WordCountResult(freq.toMap)
}