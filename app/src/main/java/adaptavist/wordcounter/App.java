package adaptavist.wordcounter;

import java.io.IOException;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        var options = new Options();
        if (!options.Parse(args)) {
            for (var error : options.getErrors()) {
                System.err.println(error);
            }
            return;
        }

        var fileHelper = new FileHelper();
        try {
            fileHelper.CheckFilePathIsOk(options.getInputFile());
        } catch (IOException e) {
            System.err.println("ERROR: There is a problem with the input file");
            System.err.println(e.getMessage());
            return;
        }

        Map<String,Integer> wordCount = null;
        try {
            wordCount = fileHelper.CountWordsInFile(options.getInputFile());
        } catch (IOException e) {
            System.err.println("ERROR: A problem occured reading the file");
            System.err.println(e.getMessage());
            return;
        }

        printOutput(wordCount, fileHelper, options.getOutputFile());
    }

    private static void printOutput (Map<String,Integer> wordCounter, FileHelper fileHelper, String outputFile) {
        var wordCountFormatter = new WordCountFormatter();
        var output = wordCountFormatter.FormatWordCounts(wordCounter);
        if (outputFile != "") {
            fileHelper.PrintToFile(output, outputFile);
        } else {
            for (var line : output) {
                System.out.println(line);
            }
        }
    }
}
