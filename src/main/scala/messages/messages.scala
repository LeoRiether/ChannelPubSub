package messages

class Message

case class LoadFile(path: String) extends Message
case class FileLoaded(content: String) extends Message
case class FoundWord(word: String) extends Message
case class ValidWord(word: String) extends Message
case class EndOfFile() extends Message
case class WordCountResult(result: Map[String, Int]) extends Message

import channel._
class PubSub {
    val message = new Channel[Message]

    def messageChild[M <: Message]() = new ChildChannel[M, Message](message)

    val loadFile = messageChild[LoadFile]
    val fileLoaded = messageChild[FileLoaded]
    val foundWord = messageChild[FoundWord]
    val validWord = messageChild[ValidWord]
    val endOfFile = messageChild[EndOfFile]
    val wordCountResult = messageChild[WordCountResult]
}