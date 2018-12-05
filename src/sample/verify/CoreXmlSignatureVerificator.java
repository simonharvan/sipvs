package sample.verify;

import com.sun.xml.internal.bind.IDResolver;
import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sample.Utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CoreXmlSignatureVerificator implements Verificator {

    public CoreXmlSignatureVerificator() {
        Init.init();
    }

    @Override
    public void verify(File fileToVerify, VerificatorCallback callback) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;


        try {
            docBuilder = docFactory.newDocumentBuilder();
            byte[] bytes = Utils.readResource(fileToVerify.getAbsolutePath()).getBytes(StandardCharsets.UTF_8);
            Canonicalizer c14n = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            ByteArrayInputStream source = new ByteArrayInputStream(c14n.canonicalize(bytes));
            Document document = docBuilder.parse(source);
            NodeList manifests = document.getElementsByTagName("ds:Manifest");
            for (int i = 0; i < manifests.getLength(); i++) {
                Node reference = getChildElementByTag(manifests.item(i).getChildNodes(), "ds:Reference");
                if (reference == null) {
                    callback.callback(false, "No reference tag");
                    return;
                }
                String uri = null;
                uri = reference.getAttributes().getNamedItem("URI").getNodeValue();

                Node node = getElementById(document.getChildNodes(), uri.substring(1));
                if (node == null) {
                    callback.callback(false, "Element with Id="+uri.substring(1)+ " doesn't exist");
                    return;
                }

                byte[] digested = digest.digest(node.getTextContent().getBytes());
                Node digestValue = getChildElementByTag(reference.getChildNodes(), "ds:DigestValue");
                if (digestValue == null) {
                    callback.callback(false, "Element with ds:DigestValue doesn't exist");
                    return;
                }

                byte[] toControl = digestValue.getTextContent().getBytes();
                if (Arrays.equals(digested, toControl)) {
                    callback.callback(false, "Uri: " + uri + " is not the same as digest value in manifest");
                    return;
                }
            }
        } catch (SAXException | InvalidCanonicalizerException | IOException | ParserConfigurationException | CanonicalizationException | NoSuchAlgorithmException e) {
            callback.callback(false, e.getMessage());
        }
        callback.callback(true, "ds:Manifest odtlačky ds:DigestValue ma rovnake hodnoty ako ich referencie");

    }


    private Node getChildElementByTag(NodeList nodes, String tag) {
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeName().equals(tag)) {
                return nodes.item(i);
            }
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getChildNodes().getLength() > 0) {
                return getChildElementByTag(nodes.item(i).getChildNodes(), tag);
            }
        }
        return null;
    }

    private Node getElementById(NodeList nodes, String id) {

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getAttributes() != null && nodes.item(i).getAttributes().getLength() > 0) {
                for (int j = 0; j < nodes.item(i).getAttributes().getLength(); j++) {
                    if (nodes.item(i).getAttributes().item(j).getNodeName().equals("Id") &&
                            nodes.item(i).getAttributes().item(j).getNodeValue().equals(id)) {
                        return nodes.item(i);
                    }
                }
            }
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getChildNodes().getLength() > 0) {
                return getElementById(nodes.item(i).getChildNodes(), id);
            }
        }
        return null;
    }
}
