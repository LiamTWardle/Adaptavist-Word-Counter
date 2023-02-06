package adaptavist.wordcounter;

import java.io.IOException;
import java.util.Map;

public class AdaptavistWordCounter {

    public static void main(String[] args) {

        var options = new Options();
        if (!options.Parse(args)) {
            System.out.print(options.getHelpText());
            if (options.isShowHelp()) {
                return;
            }
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

        printOutput(wordCount, fileHelper, options.getOutputFile(), options.getSortBy(), options.getOrder());
    }

    private static void printOutput (Map<String,Integer> wordCounter, FileHelper fileHelper, String outputFile, SortBy sortBy, Order order) {
        var wordCountFormatter = new WordCountFormatter();
        var output = wordCountFormatter.FormatWordCounts(wordCounter, sortBy, order);
        if (outputFile != "") {
            try {
                fileHelper.PrintToFile(output, outputFile);
            } catch (IOException e) {
                System.err.println("ERROR: A problem occured writing to the file");
                e.getMessage();
            }
        } else {
            for (var line : output) {
                System.out.println(line);
            }
        }
    }
}
