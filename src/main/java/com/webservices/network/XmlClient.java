package com.webservices.network;

import com.webservices.model.Student;
import com.webservices.service.XmlTransformer;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.*;

/** Receives XML over TCP, validates it against XSD, unmarshals it and prints its values. */
public class XmlClient {
    public static void main(String[] args) throws Exception {
        Path received = Path.of("received-student.xml");

        // Connect to the server and save the received bytes as an XML file.
        try (Socket socket = new Socket("localhost", 5000);
             InputStream input = socket.getInputStream()) {
            Files.copy(input, received, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Received XML: " + received.toAbsolutePath());
        }

        // Load the XSD schema and pass it to the transformer for validation.
        Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(Path.of("src/main/resources/student.xsd").toFile());
        Student student = new XmlTransformer().transformToPOJO(received.toFile(), schema);

        // Printing the values proves that the XML was successfully converted back into a POJO.
        System.out.println("Student: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Average grade: " + student.getAverageGrade());
        System.out.println("Subjects:");
        student.getSubjects().forEach(s ->
                System.out.println(s.getId() + ". " + s.getName() + " - " + s.getGrade()));
    }
}
