package main

import messages._

class StopWordFilter(val ps: PubSub) {

    val stopWords = Set("the", "about", "above", "after", "again", "against",
        "all", "and", "any", "because", "before", "below", "between", "but",
        "down", "during", "for", "from", "further", "here", "into", "more","once",
        "only", "other", "over", "same", "some", "such", "that", "then",
        "there", "these", "this", "those", "through", "under", "until", "very",
        "what", "when", "where", "which", "while", "who", "which",
        "with", "could", "were", "your", "have", "will", "been", "would",
        "they", "their", "should", "myself", "them", "upon", "might",
        "first", "eyes", "every", "you", "than", "thought", "whom", "ever",
        "most", "even","said", "shall", "towards", "found", "being",
        "time", "also", "him", "her", "still", "must", "many")

    ps.foundWord >> filter

    def filter(w: FoundWord) = {
        if (!(stopWords contains w.word))
            ps.validWord << ValidWord(w.word)
    }
}