package adaptavist.wordcounter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {

    @Test
    public void wordCounterCountsWordsInLine() {
        var wordCounter = new WordCounter();
        wordCounter.CountWords("foo foo bar foo");
        var wordCounts = wordCounter.GetWordCounts();
        assertEquals(3, wordCounts.get("foo"));
        assertEquals(1, wordCounts.get("bar"));
    }

    @Test
    public void wordCounterCountsWordsOverMultipleLines() {
        var wordCounter = new WordCounter();
        wordCounter.CountWords("foo foo bar foo");
        wordCounter.CountWords("foo foo foo bar bar");
        wordCounter.CountWords("foo lorum bar foo");
        var wordCounts = wordCounter.GetWordCounts();
        assertEquals(8, wordCounts.get("foo"));
        assertEquals(4, wordCounts.get("bar"));
        assertEquals(1, wordCounts.get("lorum"));
    }

    @Test
    public void wordCounterIgnoresCase() {
        var wordCounter = new WordCounter();
        wordCounter.CountWords("FOo Foo bAr foo");
        wordCounter.CountWords("foO LOrum bar foo");
        wordCounter.CountWords("fOo FOO foo BAr BAR");
        var wordCounts = wordCounter.GetWordCounts();
        assertEquals(8, wordCounts.get("foo"));
        assertEquals(4, wordCounts.get("bar"));
        assertEquals(1, wordCounts.get("lorum"));
    }

    @Test
    public void wordCounterIgnoresPunctuation() {
        var wordCounter = new WordCounter();
        wordCounter.CountWords("foo foo bar foo.");
        wordCounter.CountWords("foo fo'o   foo, bar bar");
        wordCounter.CountWords("foo lorum!! bar foo!?");
        var wordCounts = wordCounter.GetWordCounts();
        assertEquals(8, wordCounts.get("foo"));
        assertEquals(4, wordCounts.get("bar"));
        assertEquals(1, wordCounts.get("lorum"));
    }
}
