package main

import messages._

class WordSplitter(val ps: PubSub) {
    ps.fileLoaded >> fileLoaded

    def fileLoaded(f: FileLoaded) {
        f.content
         .toLowerCase
         .filter(c => c.isLetter || c == ' ')
         .split(' ')
         .foreach(w => ps.foundWord << FoundWord(w))

        ps.endOfFile << EndOfFile()
    }
}
