package zen.ilgo.star;

import java.io.File;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
 
import zen.ilgo.star.Stardict;
import zen.ilgo.star.StarFactory;

/**
 * Commandline tool to do some basic xml <-> stardict transformation
 * Imports and Exports stardict to and from binary, to and from xml file 
 *
 * @author roger holenweger (ilgo711@gmail.com)
 * @since Aug 11, 2014
 */
public class CmdLine {

    public static void main(String[] args) {

        CommandLineParser parser = new PosixParser();

        Options options = new Options();
        options.addOption( "i", "import", true, "create Stardict from XmlFile");
        options.addOption( "e", "export", true, "create XmlFile from Stardict");
        options.addOption( "s", "starHome", true, "directory where the stardicts are found");
        options.addOption( "h", "help", false, "print usage string");

        try {
            CommandLine cmd = parser.parse(options, args);
            String starHome = cmd.getOptionValue('s', StarFactory.STAR_HOME);

            if (cmd.hasOption("h")) {
                help(options);
                
            } else if (cmd.hasOption("i")) {
                String xmlFileName = cmd.getOptionValue("i");
                File xmlFile = new File(xmlFileName);
                importFromXml(xmlFile, starHome); 

            } else if (cmd.hasOption("e")) {
                String dictName = cmd.getOptionValue("e");
                exportToXml(starHome, dictName);

            } else {
                help(options);
            }
        } catch(ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }
    }

    /**
     * Create all Files needed for a Stardict dictionary from an xml
     *
     * @param xmlFile the xml file to be converted to a Stardict binary
     * @param starHome the directory where the stardicts are located
     */
    private static void importFromXml(File xmlFile, String starHome) {
        String oldHome = StarFactory.STAR_HOME;
        StarFactory.setStarHome(starHome);
        Stardict star = StarFactory.build(xmlFile);
        if (star == null) {
            StarFactory.STAR_HOME = oldHome;
            throw new NullPointerException("No Stardict created");
        }
        StarFactory.export(star, starHome);
        StarFactory.setStarHome(oldHome);
    }

    /**
     * Create an xml file from all files of a Stardict dictionary
     *
     * @param starHome the directory where the stardicts are located
     * @param dictName the Stardict to be writeen to xml
     */
    private static void exportToXml(String starHome, String dictName) {

        Stardict star = StarFactory.build(starHome, dictName);
        if (star == null) {
            throw new NullPointerException("No Stardict created");
        }
        File xmlFile = new File("./" + dictName + ".xml");
        StarFactory.export(star, xmlFile);
    }

    /**
     * Print the CLI usage
     *
     * @param options the Option parameters
     */
    private static void help(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("star.jar", options);
    }
}

