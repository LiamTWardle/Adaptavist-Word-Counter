package adaptavist.wordcounter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsTester {

    private String validInputFile = "src/test/resources/example.txt";

    private String validOutputFile = "src/test/resources/output.txt";

    @Test
    public void inputParsedFromArgument() {
        var options = new Options();
        String[] args = {validInputFile};
        assertTrue(options.Parse(args));
        assertTrue(options.getErrors().size() == 0);
        assertEquals(validInputFile, options.getInputFile());
    }

    @Test
    public void outputParsedFromArgument() {
        var options = new Options();
        String[] args = {validInputFile, "--output", validOutputFile};
        assertTrue(options.Parse(args));
        assertTrue(options.getErrors().size() == 0);
        assertEquals(validInputFile, options.getInputFile());
        assertEquals(validOutputFile, options.getOutputFile());
    }

    @Test
    public void outputOptionFailsWithoutFilepath() {
        var options = new Options();
        String[] args = {validInputFile, "--output"};
        assertFalse(options.Parse(args));
        assertTrue(options.getErrors().size() > 0);
    }

    @Test
    public void sortAlphabeticallyParsedFromArgument() {
        var options = new Options();
        String[] args = {validInputFile, "--alphabetical"};
        assertTrue(options.Parse(args));
        assertTrue(options.getErrors().size() == 0);
        assertEquals(validInputFile, options.getInputFile());
        assertEquals(SortBy.ALPHABETICALLY, options.getSortBy());
        assertEquals(Order.ASC, options.getOrder());
    }

    @Test
    public void sortAscParsedFromArgument() {
        var options = new Options();
        String[] args = {validInputFile, "--order", "asc"};
        assertTrue(options.Parse(args));
        assertTrue(options.getErrors().size() == 0);
        assertEquals(validInputFile, options.getInputFile());
        assertEquals(SortBy.FREQUENCY, options.getSortBy());
        assertEquals(Order.ASC, options.getOrder());
    }

    @Test
    public void sortAlphabeticallyDescParsedFromArgument() {
        var options = new Options();
        String[] args = {validInputFile, "--order", "desc", "--alphabetical"};
        assertTrue(options.Parse(args));
        assertTrue(options.getErrors().size() == 0);
        assertEquals(validInputFile, options.getInputFile());
        assertEquals(SortBy.ALPHABETICALLY, options.getSortBy());
        assertEquals(Order.DESC, options.getOrder());
    }
}
