package sample.verify;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import sample.Utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SignatureValueVerificator implements Verificator {

    @Override
    public void verify(File fileToVerify, VerificatorCallback callback) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();

            ByteArrayInputStream source = new ByteArrayInputStream(Utils.readResource(fileToVerify.getAbsolutePath()).getBytes(StandardCharsets.UTF_8));
            Document document = docBuilder.parse(source);

            Node signatureValue = document.getElementsByTagName("ds:SignatureValue").item(0);
            Node id = signatureValue.getAttributes().getNamedItem("Id");

            if (id == null){
                callback.callback(false, "Koreňový element NEobsahuje atribút Id podľa profilu XADES_ZEP.");
            }else {
                callback.callback(true, "Koreňový element obsahuje atribút Id podľa profilu XADES_ZEP.");
            }
        } catch (ParserConfigurationException | SAXException e) {
            callback.callback(false, e.getLocalizedMessage());
        } catch (IOException e) {
            callback.callback(false, "Unable to read file");
        }




    }
}
