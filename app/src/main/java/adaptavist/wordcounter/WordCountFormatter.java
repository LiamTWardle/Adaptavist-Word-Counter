package adaptavist.wordcounter;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class WordCountFormatter {

    public String[] FormatWordCounts(Map<String, Integer> wordCounts, SortBy sortBy) {
        var sortedWordCounts = sortWordCounts(wordCounts, sortBy);
        return formatWordCounts(sortedWordCounts);
    }

    private LinkedHashMap<String, Integer> sortWordCounts(Map<String, Integer> wordCounts, SortBy sortBy) {
        Stream<Entry<String, Integer>> sorted = null;
        switch (sortBy) {
            case FREQUENCY:
                sorted =  wordCounts.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
                break;
            case ALPHABETICALLY:
                sorted =  wordCounts.entrySet().stream().sorted(Map.Entry.comparingByKey());
                break;
            default:
                throw new RuntimeException("Unexpected ordering: " + sortBy);
        }
        var sortedWordCounts = new LinkedHashMap<String, Integer>();
        sorted.forEachOrdered(wc -> sortedWordCounts.put(wc.getKey(), wc.getValue()));
        return sortedWordCounts;
    }

    private String[] formatWordCounts(LinkedHashMap<String, Integer> sortedWordCounts) {
        var formattedWordCounts = new String[sortedWordCounts.size()];
        int i = 0;
        for (var word : sortedWordCounts.keySet()) {
            formattedWordCounts[i] = word + ": " + sortedWordCounts.get(word);
            i++;
        }
        return formattedWordCounts;
    }
}
