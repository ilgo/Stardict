#Stardict

A command line tool to transform xml files from and to stardict format. 

* Originally the binary format was used by an application known as Stardict.
  These days many Opensource DictReader binaries are available that can read this format.
  To mention is [Golden Dict] (http://goldendict.org/).

* The xml format for the raw input is validated against resources/stardict.xsd. 
  This xml schema is not part of the original stardict format, and will support synonym files. 
  There are a few samples in test/resources to see how the xml file is structured.


###Dependencies
The following libraries will be needed:
* /usr/share/java/commons-cli.jar
* /usr/share/java/junit4.jar


###Build
Build by running __ant__ in the root of the project.
It will create an executable ./star.jar, that accepts the following parameters.

```
usage: star.jar
 -e,--export <arg>     create XmlFile from Stardict
 -h,--help             print usage string
 -i,--import <arg>     create Stardict from XmlFile
 -s,--starHome <arg>   directory where the stardicts are found
```

###Tests
The tests can be run by __ant__ __test__. 
For the tests to pass the ~/.stardict/dic directory must exist.
 

###Examples:
* Export an existing Stardict with given name to xml. A valid stardict is a at least a triple of files with the following file-expansions. _.ifo_, _.idx_, _dict_ and possibly a _syn_.
If either the idx or dict files are zipped, they must be first unzipped.

```./star.jar -e yusig -s /home/data/dict```                       => creates yusig.xml in the current directory.


* Import a dictionary from xml. The xml will be validated against ./resources/startdict.xsd.
 
```./star-jar -i yusig.xml -s /home/creator/of/many/dictionaries``` => creates the stardict binaries from the xml input.

