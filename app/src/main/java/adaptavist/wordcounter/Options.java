package adaptavist.wordcounter;

import java.util.ArrayList;
import java.util.List;

public class Options {

    private List<String> errors = new ArrayList<>();

    private String inputFile = "";

    private static final String OUTPUT_OPT = "--output";
    private static final String OUTPUT_OPT_SHORT = "-o";
    private String outputFile = "";

    private static final String ALPHABETICALLY_OPT = "--alphabetically";
    private static final String ALPHABETICALLY_OPT_ALT = "--alphabetical";
    private static final String ALPHABETICALLY_OPT_SHORT = "-a";
    private SortBy sortBy = SortBy.FREQUENCY;

    private static final String ORDER_OPT = "--order";
    private Order order = Order.DESC;
    private boolean orderSpecified = false;

    private static final String HELP_OPT = "--help";
    private static final String HELP_OPT_SHORT = "-h";
    private boolean showHelp = false;

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
                case ALPHABETICALLY_OPT:
                case ALPHABETICALLY_OPT_ALT:
                case ALPHABETICALLY_OPT_SHORT:
                    parseAlphabetically();
                    break;
                case ORDER_OPT:
                    parseOrder(args, i);
                    i++;
                    break;
                case HELP_OPT:
                case HELP_OPT_SHORT:
                    parseHelp();
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
        if (!nextArgumentIsValid(args, i)) {
            errors.add("Output file must be specified to use " + args[i]);
            return;
        }
        if (outputFile != "") {
            errors.add("Only one output file can be specified");
            return;
        }
        outputFile = args[i + 1];
    }

    private boolean nextArgumentIsValid(String[] args, int i) {
        return args.length > i + 1 && !args[i + 1].startsWith("-");
    }

    private void parseAlphabetically() {
        sortBy = SortBy.ALPHABETICALLY;
        if (!orderSpecified) {
            order = Order.ASC;
        }
    }

    private void parseOrder(String[] args, int i) {
        if (!nextArgumentIsValid(args, i)) {
            errors.add("Order must be specified to use " + args[i]);
            return;
        }
        switch(args[i + 1]) {
            case "asc":
            case "ASC":
            case "ascending":
            case "ASCENDING":
                order = Order.ASC;
                break;
            case "desc":
            case "DESC":
            case "descending":
            case "DESCENDING":
                order = Order.DESC;
                break;
            default:
                errors.add("Order must be either ASC or DESC");
        }
        orderSpecified = true;
    }

    private void parseHelp() {
        showHelp = true;
    }

    public String getHelpText() {
        return "USAGE: java -jar AdaptavistWordCounter.jar [options] input_file_path\n"
                + "  options:\n"
                + "    " + HELP_OPT_SHORT + ", " + HELP_OPT + ":              show this help message.\n"
                + "    " + OUTPUT_OPT_SHORT + ", " + OUTPUT_OPT + ":            file to write output to.\n"
                + "    " + ALPHABETICALLY_OPT_SHORT + ", " + ALPHABETICALLY_OPT + ":    sort alphabetically (default ascending).\n"
                + "    " + ORDER_OPT + ":                 order results ascending or descending.\n"
                ;
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

    public SortBy getSortBy() {
        return sortBy;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isShowHelp() {
        return showHelp;
    }
}
