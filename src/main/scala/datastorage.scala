package main

import messages._

import scala.io.Source

class DataStorage(val ps: PubSub) {
    ps.loadFile >> loadFile

    def loadFile(file: LoadFile): Unit = {
        val content = Source.fromFile(file.path).mkString
        ps.fileLoaded << FileLoaded(content)
    }
}