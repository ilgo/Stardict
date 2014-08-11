package zen.ilgo.star;

import java.io.File;

import zen.ilgo.star.Stardict;
import zen.ilgo.star.StarFactory;


public class CmdLine {

    public static void main(String[] args) {


    }

    /**
     * Create all Files needed for a Stardict dictionary from an xml
     *
     * @param fileName the xml file to be converted to a Stardict binary
     */
    private void importFromXml(String fileName) {

        Stardict star = StarFactory.build(fileName);
        StarFactory.export(star);
    }

    /**
     * Create an xml file from all files of a Stardict dictionary
     *
     * @param dictName the Stardict to be writeen to xml
     */
    private void exportToXml(String dictName) {

        Stardict star = StarFactory.build(dictName);
        File xmlFile = new File("./" + dictName + ".xml");
        StarFactory.export(star, xmlFile);
    }
}
