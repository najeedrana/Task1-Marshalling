package com.webservices.service;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;

/** Validates an XML file against a DTD using a validating SAX parser. */
public class DtdValidator {
    /** Parses the XML with validation enabled and supplies the chosen local DTD file. */
    public void validate(File xmlFile, File dtdFile) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        XMLReader reader = factory.newSAXParser().getXMLReader();

        // Resolve the document's external DTD reference to the local DTD supplied by this project.
        reader.setEntityResolver((publicId, systemId) -> new InputSource(new FileInputStream(dtdFile)));
        reader.parse(xmlFile.toURI().toString()); // Invalid XML throws a parsing/validation exception.
    }
}
