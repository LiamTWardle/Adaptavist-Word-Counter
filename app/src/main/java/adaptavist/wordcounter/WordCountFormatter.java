package adaptavist.wordcounter;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class WordCountFormatter {

    public String[] FormatWordCounts(Map<String, Integer> wordCounts, SortBy sortBy, Order order) {
        var sortedWordCounts = sortWordCounts(wordCounts, sortBy, order);
        return formatWordCounts(sortedWordCounts);
    }

    private LinkedHashMap<String, Integer> sortWordCounts(Map<String, Integer> wordCounts, SortBy sortBy, Order order) {
        var stream = wordCounts.entrySet().stream();
        Stream<Entry<String, Integer>> sorted = null;
        switch (sortBy) {
            case FREQUENCY:
                if (order == Order.ASC) {
                    sorted =  stream.sorted(Map.Entry.comparingByValue());
                } else {
                    sorted = stream.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
                }
                break;
            case ALPHABETICALLY:
                if (order == Order.ASC) {
                    sorted =  stream.sorted(Map.Entry.comparingByKey());
                } else {
                    sorted = stream.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()));
                }
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
