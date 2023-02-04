package adaptavist.wordcounter;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCountFormatter {

    public String[] FormatWordCounts(Map<String, Integer> wordCounts) {
        var sortedWordCounts = sortWordCounts(wordCounts);
        return formatWordCounts(sortedWordCounts);
    }

    private LinkedHashMap<String, Integer> sortWordCounts(Map<String, Integer> wordCounts) {
        var sortedWordCounts = new LinkedHashMap<String, Integer>();
        wordCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(wc -> sortedWordCounts.put(wc.getKey(), wc.getValue()));
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
