Stardict
========

A command line tool to transform xml files from and into stardict format. 

* Originally the binary format was used by an application known as Stardict.
  These days many Opensource DictReader binaries are available that can read this format.
  To mention is Goldendict, http://goldendict.org/

* The xml format for the raw input is validated against resources/stardict.xsd. 
  This xml schema is not part of the original stardict format, and will support synonym files. 
  There are a few samples in test/resources to see how the xml file is tructured.


Build by running 'ant' in the root of the project.
It will create an executable ./star.jar.

usage: star.jar
 -e,--export <arg>     create XmlFile from Stardict
 -h,--help             print usage string
 -i,--import <arg>     create Stardict from XmlFile
 -s,--starHome <arg>   directory where the stardicts are found

examples:
Export an existing Stardict with given name to xml. A valid stardict is a at least a triple of files with the following file-expansions. '.ifo', '.idx', 'dict'. If either the idx or dict files are zipped, they must be first unzipped.

./star.jar -e yusig -s /home/data/dict => will create yusig.xml in the current directory.
./star-jar -i usig.xml -s /home/creator/of/many/dictionaries => creates the stardict binaries from the xml input.

