package adaptavist.wordcounter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class WordCountFormatterTest {

    @Test
    public void TestWordCountsAreSortedInOrderOfFrequency () {
        var wordCounterPrinter = new WordCountFormatter();
        var wordCounts = new HashMap<String, Integer>();
        wordCounts.put("foo", 1);
        wordCounts.put("bar", 5);
        wordCounts.put("lorem", 3);
        wordCounts.put("ipsum", 7);
        wordCounts.put("dolor", 100);
        wordCounts.put("sit", 2);
        wordCounts.put("amet", 13);
        var formattedWordCounts = wordCounterPrinter.FormatWordCounts(wordCounts, SortBy.FREQUENCY);
        assertEquals("dolor", formattedWordCounts[0].split(":")[0]);
        assertEquals("amet", formattedWordCounts[1].split(":")[0]);
        assertEquals("ipsum", formattedWordCounts[2].split(":")[0]);
        assertEquals("bar", formattedWordCounts[3].split(":")[0]);
        assertEquals("lorem", formattedWordCounts[4].split(":")[0]);
        assertEquals("sit", formattedWordCounts[5].split(":")[0]);
        assertEquals("foo", formattedWordCounts[6].split(":")[0]);
    }

    @Test
    public void TestWordCountsAreSortedAlphabetically () {
        var wordCounterPrinter = new WordCountFormatter();
        var wordCounts = new HashMap<String, Integer>();
        wordCounts.put("foo", 1);
        wordCounts.put("bar", 5);
        wordCounts.put("lorem", 3);
        wordCounts.put("ipsum", 7);
        wordCounts.put("dolor", 100);
        wordCounts.put("sit", 2);
        wordCounts.put("amet", 13);
        var formattedWordCounts = wordCounterPrinter.FormatWordCounts(wordCounts, SortBy.ALPHABETICALLY);
        assertEquals("amet", formattedWordCounts[0].split(":")[0]);
        assertEquals("bar", formattedWordCounts[1].split(":")[0]);
        assertEquals("dolor", formattedWordCounts[2].split(":")[0]);
        assertEquals("foo", formattedWordCounts[3].split(":")[0]);
        assertEquals("ipsum", formattedWordCounts[4].split(":")[0]);
        assertEquals("lorem", formattedWordCounts[5].split(":")[0]);
        assertEquals("sit", formattedWordCounts[6].split(":")[0]);
    }
}
