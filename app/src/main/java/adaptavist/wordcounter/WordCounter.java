package adaptavist.wordcounter;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    private Map<String, Integer> wordCounts;

    public WordCounter () {
        wordCounts= new HashMap<>();
    }

    public void CountWords(String line) {
        var words = line.replaceAll("[^\\p{L} ]", "").toLowerCase().split("\\s+");
        for (var word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
    }

    public Map<String, Integer> GetWordCounts() {
        return wordCounts;
    }
}
