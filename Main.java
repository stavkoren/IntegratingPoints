import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringJoiner;

import org.apache.commons.cli.*;

import javax.xml.transform.stream.StreamResult;


public class Main {
    private static final String XML_FILE_FLAG = "x";
    private static final String OUTPUT_FILE_FLAG = "o";
    private static final String HELP_FLAG = "h";

    public static void main(String[] args) throws IOException {
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
        if(cmd.hasOption(HELP_FLAG)) {
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
        var s = b.updateReports();


        StreamResult sr;
        //if output file didn't set - use console.
        if (cmd.hasOption(OUTPUT_FILE_FLAG)) {
            try {
                sr = new StreamResult(new File(cmd.getOptionValue(OUTPUT_FILE_FLAG)));
            } catch (Exception e) {
                System.err.println("Error when create output file");
                return;
            }
        } else {
            sr = new StreamResult(System.out);
        }


        WriteReports wr = new WriteReports();
        wr.write(s);
        wr.save(sr);

    }

    //CLI Options
    private static Options getCLIOptions() {
        Options options = new Options();
        options.addOption(Option.builder(OUTPUT_FILE_FLAG).hasArg().argName("file").desc("output file. if didn't set - use console").build());
        options.addOption(Option.builder(XML_FILE_FLAG).hasArg().argName("file").desc("xml file").build());
        options.addOption(Option.builder(HELP_FLAG).desc("print this message").build());
        return options;
    }
}
