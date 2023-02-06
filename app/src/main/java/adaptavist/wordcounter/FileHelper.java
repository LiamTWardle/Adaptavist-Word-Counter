package adaptavist.wordcounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Scanner;

public class FileHelper {

    public void CheckFilePathIsOk (String filePath) throws IOException {
        var file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("file not found");
        }
        var mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null || !mimeType.startsWith("text")) {
            throw new IOException("file is not a text file");
        }
    }

    public Map<String, Integer> CountWordsInFile (String filePath) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        var wordCounter = new WordCounter();
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
        return wordCounter.GetWordCounts();
    }

    public void PrintToFile(String[] output, String outputFile) throws IOException {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFile);
            for (var line : output) {
                line += "\n";
                outputStream.write(line.getBytes());
            }
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
