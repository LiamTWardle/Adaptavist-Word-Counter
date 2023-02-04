package adaptavist.wordcounter;

import java.io.IOException;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("ERROR: Expected only one argument");
            return;
        }

        var filePath = args[0];
        var fileHelper = new FileHelper();
        try {
            fileHelper.CheckFilePathIsOk(filePath);
        } catch (IOException e) {
            System.err.println("ERROR: There is a problem with the input file");
            System.err.println(e.getMessage());
            return;
        }

        Map<String,Integer> wordCount = null;
        try {
            wordCount = fileHelper.CountWordsInFile(filePath);
        } catch (IOException e) {
            System.err.println("ERROR: A problem occured reading the file");
            System.err.println(e.getMessage());
            return;
        }

        printOutput(wordCount);
    }

    private static void printOutput (Map<String,Integer> wordCounter) {
        var wordCountFormatter = new WordCountFormatter();
        var output = wordCountFormatter.FormatWordCounts(wordCounter);
        for (var line : output) {
            System.out.println(line);
        }
    }
}
