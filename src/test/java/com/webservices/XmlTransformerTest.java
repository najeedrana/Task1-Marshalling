package com.webservices;

import com.webservices.model.Student;
import com.webservices.service.DtdValidator;
import com.webservices.service.XmlTransformer;
import org.junit.jupiter.api.Test;
import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for XML conversion and DTD/XSD validation. */
class XmlTransformerTest {
    @Test
    void convertsPojoToXmlAndBackWithTenSubjects() throws Exception {
        File xml = File.createTempFile("student", ".xml");
        XmlTransformer transformer = new XmlTransformer();
        Student original = Main.createSampleStudent();
        transformer.transformToXML(original, xml);
        var schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File("src/main/resources/student.xsd"));
        Student restored = transformer.transformToPOJO(xml, schema);
        assertEquals(original.getName(), restored.getName());
        assertEquals(10, restored.getSubjects().size());
        xml.delete();
    }

    @Test
    void validatesXmlAgainstDtd() throws Exception {
        File xml = new File("student.xml");
        new XmlTransformer().transformToXML(Main.createSampleStudent(), xml);
        assertDoesNotThrow(() -> new DtdValidator().validate(
                xml, new File("src/main/resources/student.dtd")));
    }
}
