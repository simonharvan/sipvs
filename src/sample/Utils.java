package sample;

import org.apache.commons.io.FileUtils;
import org.apache.xml.security.utils.Base64;
import org.bouncycastle.tsp.TimeStampResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public static String getTimeStamp(String message) {

        String timeStampBase64 = null;

        try {
            URL url = new URL("http://test.ditec.sk/timestampws/TS.aspx");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/timestamp-query");
            con.setRequestProperty("Content-length", String.valueOf(message.length()));
            OutputStream out = con.getOutputStream();
            out.write(message.getBytes());
            out.flush();

            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP Error");
            }

            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null){
                result.append(line);
            }

            return String.valueOf(Base64.decode(result.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return timeStampBase64;
    }

    public static void saveFile(String fileName, String data) throws IOException {
        FileUtils.write(new File(fileName), data, "UTF-8");
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

    public static void appendTimeStampToFile(String signedFileName, String fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(Utils.readResource(signedFileName)));
            Document document = docBuilder.parse(source);

            // Ziskanie elementu xades:QualifyingProperties
            org.w3c.dom.Node qualifyingProperties = document.getElementsByTagName("xades:QualifyingProperties").item(0);

            if (qualifyingProperties == null) {
                return;
            }

            // Vytvorenie podelementov
            Element unsignedProperties = document.createElement("xades:UnsignedProperties");
            Element unsignedSignatureProperties = document.createElement("xades:UnsignedSignatureProperties");
            Element signatureTimestamp = document.createElement("xades:SignatureTimeStamp");
            Element encapsulatedTimeStamp = document.createElement("xades:EncapsulatedTimeStamp");

            // Priradenie podelementov
            unsignedProperties.appendChild(unsignedSignatureProperties);
            unsignedSignatureProperties.appendChild(signatureTimestamp);
            signatureTimestamp.appendChild(encapsulatedTimeStamp);

            // Ziskanie samotnej peciatky a vlozenie do dokumentu
            org.w3c.dom.Node signatureValue = document.getElementsByTagName("ds:SignatureValue").item(0);

            if (signatureValue == null) {
                return;
            }


            String timestamp = Utils.getTimeStamp(signatureValue.getTextContent());

            Text signatureNode = document.createTextNode(timestamp);
            encapsulatedTimeStamp.appendChild(signatureNode);

            qualifyingProperties.appendChild(unsignedProperties);

            // Opatovne vytvorenie XML dokumentu
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new StringWriter());

            transformer.transform(domSource, result);
            Utils.saveFile(fileName, result.getWriter().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
