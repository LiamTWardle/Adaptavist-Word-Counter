package adaptavist.wordcounter;

import java.util.ArrayList;
import java.util.List;

public class Options {

    private List<String> errors = new ArrayList<>();

    private String inputFile = "";

    private static final String OUTPUT_OPT = "--output";
    private static final String OUTPUT_OPT_SHORT = "-o";
    private String outputFile = "";

    public boolean Parse (String[] args) {
        if (args == null || args.length == 0) {
            errors.add("Input file not specified");
            return false;
        }
        for(int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case OUTPUT_OPT:
                case OUTPUT_OPT_SHORT:
                    parseOutput(args, i);
                    i++;
                    break;
                default:
                    parseInput(args, i);
            }
        }
        if (inputFile == "") {
            errors.add("Input file must be specified");
        }
        return errors.size() == 0;
    }

    private void parseInput(String[] args, int i) {
        if (inputFile != "") {
            errors.add("Only one input file can be specified");
            return;
        }
        inputFile = args[i];
    }

    private void parseOutput (String[] args, int i) {
        if (args.length <= i + 1 || args[i + 1].startsWith("-")) {
            errors.add("Output file must be specified to use " + args[i]);
            return;
        }
        if (outputFile != "") {
            errors.add("Only one output file can be specified");
            return;
        }
        outputFile = args[i + 1];
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }
}
