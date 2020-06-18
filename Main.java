import java.io.File;
import java.io.PrintWriter;

import org.apache.commons.cli.*;


public class Main {
    private static final String XML_FILE_FLAG = "x";
    private static final String OUTPUT_FILE_FLAG = "o";

    public static void main(String[] args) {

        //cli parse
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(Main.getCLIOptions(), args);
        } catch (Exception e) {
            System.err.println("Error while parse CLI");
            System.err.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(" ", Main.getCLIOptions());
            return;
        }
        //check if xml file was set
        if (!cmd.hasOption(XML_FILE_FLAG)) {
            System.err.println("XML File didn't set");
            return;
        }

        var a = LoggerParser.GetReports(cmd.getOptionValue(XML_FILE_FLAG));
        var b = new InteresPointFinder(a);
        var s = b.getInteresPoints();

        PrintWriter pr;
        //if output file didn't set - use console.
        if (cmd.hasOption(OUTPUT_FILE_FLAG)){
            try {
                pr = new PrintWriter(new File(cmd.getOptionValue(OUTPUT_FILE_FLAG)));
            } catch (Exception e){
                System.err.println("Error when create output file");
                return;
            }
        } else {
            pr = new PrintWriter(System.out);
        }
        for (var p:s) {
            pr.println(p.toString());
        }
        pr.close();
    }

    //CLI Options
    private static Options getCLIOptions() {
        Options options = new Options();
        options.addOption(OptionBuilder.withArgName("file").hasArg().withDescription("xml file").create(XML_FILE_FLAG));
        options.addOption(OptionBuilder.withArgName("file").hasArg().withDescription("output file. if didn't set - use console").create(OUTPUT_FILE_FLAG));
        return options;
    }
}
