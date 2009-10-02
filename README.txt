generator-example exercises the functionality of tohu-generator and tohu.

To build and run conduct the following:
1. Download and build tohu 
2. Download and build tohu-generator
3. Run mvn install on top level of generator-example
4. Move to dir generator-example-web and run mvn jetty:run
5. Point your browser at http://localhost:8080/generator-example-web

If you wish to modify the example the following may be helpful:

The pom.xml in generator-example-rules drives the generation. There are
a set of properties you can change that control the bootstrapping of
the generator and read the Control worksheet of the spreadsheet.

If you add additional pages which result in additional rule files being 
generated you will have to modify the generator.xml configuration file
for the rule agent.

You may add additional rule templates to your project. They just need 
to be on the classpath. An important thing to remember is to avoid
nameclashes in rules. If you generate duplicate names the rule engine
will only execute the last loaded.
