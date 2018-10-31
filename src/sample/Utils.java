package sample;

import org.apache.commons.io.FileUtils;
import org.apache.xml.security.utils.Base64;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class Utils {

    static public String readResource(String name) throws IOException {
        InputStream is = getResourceAsStream(name);
        byte[] data = new byte[is.available()];
        is.read(data);
        is.close();
        return new String(data, "UTF-8");
    }
    static public InputStream getResourceAsStream(String name) {
        File path = new File(name);
        try {
            InputStream is = new FileInputStream(path);
            return is;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Nepodarilo sa otvorit zdroj: " + path);
        }
    }

    static public String readResourceAsBase64(String name) throws IOException {
        InputStream is = getResourceAsStream(name);
        byte[] data = new byte[is.available()];
        is.read(data);
        is.close();
        String msg = Base64.encode(data);
        return msg;

    }

    public static void saveFile(String fileName, String data) throws IOException {
        FileUtils.writeStringToFile(new File(fileName), data);
    }

    public static void saveXMLtoHTML(String inFilename, String outFilename, String xslFilename) {
        try {
            // Create transformer factory
            TransformerFactory factory = TransformerFactory.newInstance();

            // Use the factory to create a template containing the xsl file
            Templates template = factory.newTemplates(new StreamSource(
                    new FileInputStream(xslFilename)));

            // Use the template to create a transformer
            Transformer xformer = template.newTransformer();

            // Prepare the input and output files
            Source source = new StreamSource(new FileInputStream(inFilename));
            Result result = new StreamResult(new FileOutputStream(outFilename));

            // Apply the xsl file to the source file and write the result
            // to the output file
            xformer.transform(source, result);


        } catch (FileNotFoundException e) {
        } catch (TransformerConfigurationException e) {
            // An error occurred in the XSL file
        } catch (TransformerException e) {
            // An error occurred while applying the XSL file
            // Get location of error in input file
            SourceLocator locator = e.getLocator();
            int col = locator.getColumnNumber();
            int line = locator.getLineNumber();
            String publicId = locator.getPublicId();
            String systemId = locator.getSystemId();
        }
    }
}
