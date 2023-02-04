package adaptavist.wordcounter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FileHelperTest {

    @Test
    public void fileCheckerThrowsNoExceptionWhenCheckingOkFile() {
        var fileHelper = new FileHelper();
        String path = "src/test/resources/example.txt";
        try {
            fileHelper.CheckFilePathIsOk(path);
        } catch (Exception e) {
            fail("Exception thrown for ok filepath: " + e.getMessage());
        }
    }

    @Test
    public void fileCheckerThrowsExceptionWhenCheckingMissingFile() {
        var fileHelper = new FileHelper();
        String path = "src/test/resources/does_not_exist.txt";
        try {
            fileHelper.CheckFilePathIsOk(path);
            fail("Expected exception for file not found");
        } catch (IOException e) {
            // Pass
        }
    }

    @Test
    public void fileCheckerThrowsExceptionWhenCheckingBinaryFile() {
        var fileHelper = new FileHelper();
        String path = "src/test/resources/binary.exe";
        try {
            fileHelper.CheckFilePathIsOk(path);
            fail("Expected exception for binary file");
        } catch (IOException e) {
            // Pass
        }
    }

    @Test
    public void wordCountReturnedWhenFileIsOk() {
        var fileHelper = new FileHelper();
        String path = "src/test/resources/example.txt";
        Map<String, Integer> wordCount = null;
        try {
            wordCount = fileHelper.CountWordsInFile(path);
        } catch (Exception e) {
            fail("Exception thrown for ok filepath: " + e.getMessage());
        }
        assertNotNull(wordCount, "expected word count to not be null");
        assertTrue(wordCount.size() > 0, "expected results from word count");
    }
}
