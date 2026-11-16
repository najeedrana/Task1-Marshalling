package com.webservices;

import com.webservices.model.*;
import com.webservices.service.*;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

/** Runs the local POJO/XML conversion and schema-validation demonstration. */
public class Main {
    /** Creates a student with ten dependent subjects, satisfying the assessment's minimum. */
    public static Student createSampleStudent() {
        Student s = new Student(1,"Rana Khalid",22,8.7f,true,'R');
        s.getSubjects().add(new Subject(1,"Java",9.0f,true));
        s.getSubjects().add(new Subject(2,"Web Services",8.5f,true));
        s.getSubjects().add(new Subject(3,"Databases",8.0f,true));
        s.getSubjects().add(new Subject(4,"Networking",7.5f,true));
        s.getSubjects().add(new Subject(5,"Mathematics",8.0f,true));
        s.getSubjects().add(new Subject(6,"Algorithms",9.0f,true));
        s.getSubjects().add(new Subject(7,"Operating Systems",8.5f,true));
        s.getSubjects().add(new Subject(8,"Software Engineering",9.0f,true));
        s.getSubjects().add(new Subject(9,"Information Security",8.0f,true));
        s.getSubjects().add(new Subject(10,"Human Computer Interaction",8.5f,true));
        return s;
    }

    public static void main(String[] args) throws Exception {
        File xml = new File("student.xml");
        XmlTransformer transformer = new XmlTransformer();

        // Step 1: Java object -> XML.
        transformer.transformToXML(createSampleStudent(), xml);
        System.out.println("Created XML: " + xml.getAbsolutePath());

        // Step 2: validate XML against DTD.
        new DtdValidator().validate(xml, new File("src/main/resources/student.dtd"));
        System.out.println("DTD validation successful.");

        // Step 3: validate XML against XSD while converting XML -> Java object.
        Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File("src/main/resources/student.xsd"));
        Student restored = transformer.transformToPOJO(xml, schema);
        System.out.println("XSD validation and unmarshalling successful.");
        System.out.println("Student: " + restored.getName());
        System.out.println("Subjects restored: " + restored.getSubjects().size());
    }
}
