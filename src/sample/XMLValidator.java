package sample;




import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class XMLValidator {

    public static String validate(String xml, String xsd) {
        return validate(new File(xml), new File(xsd));
    }

    public static String validate(File xml, File xsd) {

        Source xmlFile = new StreamSource(xml);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            return xmlFile.getSystemId() + " is valid";
        } catch (SAXException e) {
            return xmlFile.getSystemId() + " is NOT valid reason:" + e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlFile.getSystemId() + " is NOT valid";
    }
}
