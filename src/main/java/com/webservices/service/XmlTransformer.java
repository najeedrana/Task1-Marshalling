package com.webservices.service;

import com.webservices.model.Student;
import jakarta.xml.bind.*;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import java.io.*;
import java.nio.charset.StandardCharsets;

/** Keeps all JAXB conversion logic in one separate service class. */
public class XmlTransformer {

    /** Converts a Student POJO to a formatted XML file and adds its DTD declaration. */
    public void transformToXML(Student student, File outputFile) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Marshal into memory first so the DOCTYPE can be inserted after the XML declaration.
        StringWriter buffer = new StringWriter();
        marshaller.marshal(student, buffer);
        String xml = buffer.toString();
        int declarationEnd = xml.indexOf("?>");
        String doctype = System.lineSeparator()
                + "<!DOCTYPE student SYSTEM \"src/main/resources/student.dtd\">";
        xml = declarationEnd >= 0
                ? xml.substring(0, declarationEnd + 2) + doctype + xml.substring(declarationEnd + 2)
                : "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + doctype + xml;

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
            writer.write(xml);
        }
    }

    /** Converts XML to a Student POJO; a supplied XSD Schema enables validation during unmarshalling. */
    public Student transformToPOJO(File inputFile, Schema schema) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        if (schema != null) unmarshaller.setSchema(schema);

        // DTD validation is demonstrated separately by DtdValidator. Disable external DTD loading here.
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XMLReader reader = factory.newSAXParser().getXMLReader();
        reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
        reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        return (Student) unmarshaller.unmarshal(
                new SAXSource(reader, new InputSource(inputFile.toURI().toString())));
    }
}
