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

public class SignatureElementVerificator implements Verificator {

    @Override
    public void verify(File fileToVerify, VerificatorCallback callback) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

        try {
            docBuilder = docFactory.newDocumentBuilder();

            ByteArrayInputStream source = new ByteArrayInputStream(Utils.readResource(fileToVerify.getAbsolutePath()).getBytes(StandardCharsets.UTF_8));
            Document document = docBuilder.parse(source);

            Node signature = document.getElementsByTagName("ds:Signature").item(0);
            Node ds = signature.getAttributes().getNamedItem("xmlns:ds");
            Node id = signature.getAttributes().getNamedItem("Id");

            if (id == null) {
                callback.callback(false, "Koreňový element NEobsahuje atribúty Id podľa profilu XADES_ZEP.");
            } else if (ds == null){
                callback.callback(false, "Koreňový element NEobsahuje atribúty xmlns:ds podľa profilu XADES_ZEP.");
            }else {
                callback.callback(true, "Koreňový element obsahuje atribúty xmlns:id a xmlns:ds podľa profilu XADES_ZEP.");
            }
        } catch (ParserConfigurationException | SAXException e) {
            callback.callback(false, e.getLocalizedMessage());
        } catch (IOException e) {
            callback.callback(false, "Unable to read file");
        }


    }
}
