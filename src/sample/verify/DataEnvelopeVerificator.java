package sample.verify;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import sample.Utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;

public class DataEnvelopeVerificator implements Verificator {

    @Override
    public void verify(File fileToVerify, VerificatorCallback callback) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();

            ByteArrayInputStream source = new ByteArrayInputStream(Utils.readResource(fileToVerify.getAbsolutePath()).getBytes(StandardCharsets.UTF_8));
            Document document = docBuilder.parse(source);

            Node envelope = document.getElementsByTagName("xzep:DataEnvelope").item(0);
            Node ds = envelope.getAttributes().getNamedItem("xmlns:ds");
            Node xzep = envelope.getAttributes().getNamedItem("xmlns:xzep");

            if (xzep == null) {
                callback.callback(false, "Koreňový element NEobsahuje atribúty xmlns:xzep podľa profilu XADES_ZEP.");
            } else if (ds == null){
                callback.callback(false, "Koreňový element NEobsahuje atribúty xmlns:ds podľa profilu XADES_ZEP.");
            }else {
                callback.callback(true, "Koreňový element obsahuje atribúty xmlns:xzep a xmlns:ds podľa profilu XADES_ZEP.");
            }
        } catch (ParserConfigurationException | SAXException e) {
            callback.callback(false, e.getLocalizedMessage());
        } catch (IOException e) {
            callback.callback(false, "Unable to read file");
        }




    }
}
