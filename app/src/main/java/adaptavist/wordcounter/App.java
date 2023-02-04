package adaptavist.wordcounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class App {

    private static WordCounter wordCounter;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("ERROR: Expected only one argument");
            return;
        }

        var filePath = args[0];
        try {
            filePathIsOk(filePath);
        } catch (IOException e) {
            System.err.println("ERROR: There is a problem with the input file");
            System.err.println(e.getMessage());
            return;
        }

        wordCounter = new WordCounter();
        try {
            readFile(filePath);
        } catch (IOException e) {
            System.err.println("ERROR: A problem occured reading the file");
            System.err.println(e.getMessage());
            return;
        }

        printOutput();
    }

    private static void filePathIsOk (String filePath) throws IOException {
        var file = new File(filePath);
        var mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null) {
            throw new IOException("file not found");
        }
        if (!mimeType.startsWith("text")) {
            throw new IOException("file is not a text file");
        }
    }

    private static void readFile (String filePath) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                wordCounter.CountWords(sc.nextLine());
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    private static void printOutput () {
        var wordCountFormatter = new WordCountFormatter();
        var output = wordCountFormatter.FormatWordCounts(wordCounter.GetWordCounts());
        for (var line : output) {
            System.out.println(line);
        }
    }
}
