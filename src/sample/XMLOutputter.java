package sample;

import javafx.scene.effect.PerspectiveTransform;
import org.jdom2.*;
import org.jdom2.output.Format;

public class XMLOutputter {

    public static void spracuj(Person customer){

        // create the jdom
        Document jdomDoc = new Document();
        // create root element
        Element rootElement = new Element("car-rent");
        jdomDoc.setRootElement(rootElement);

        // add child
        Element orderPerson = new Element("orderperson");

            Element person = new Element("person");
            orderPerson.addContent(person);

                Element name = new Element("name");
                name.addContent(customer.getName());
                person.addContent(name);

                Element date = new Element("date-of-birth");
                date.addContent("456");
                person.addContent(date);

                Element id = new Element("id");
                id.addContent(customer.getEvidenceNumber());
                person.addContent(id);

                Element email = new Element("email");
                email.addContent(customer.getEmail());
                person.addContent(email);

                Element phone = new Element("phone-number");
                phone.addContent(customer.getTelephoneNumber());
                person.addContent(phone);

        rootElement.addContent(orderPerson);

        Element passengers = new Element("passengers");

            Element passPerson = new Element("person");
            passengers.addContent(passPerson);

                Element passName = new Element("name");
                passName.addContent("John Smith");
                passPerson.addContent(passName);

                Element passDate = new Element("date-of-birth");
                passDate.addContent("09-12-1993");
                passPerson.addContent(passDate);

                Element passId = new Element("id");
                passId.addContent("EC123056");
                passPerson.addContent(passId);

                Element passEmail = new Element("email");
                passEmail.addContent("john@smith.com");
                passPerson.addContent(passEmail);

                Element passPhone = new Element("phone-number");
                passPhone.addContent("+421911099339");
                passPerson.addContent(passPhone);

        rootElement.addContent(passengers);

        //************************************
        // DOROBIT ZVYSOK ALE ROZUMNE
        //************************************


        // add attributes
        //Attribute nameAttr = new Attribute("name", "John Smith");
        //orderPerson.setAttribute(nameAttr);

        // Output as XML
        // create XMLOutputter
        org.jdom2.output.XMLOutputter xml = new org.jdom2.output.XMLOutputter();
        // we want to format the xml. This is used only for demonstration. pretty formatting adds extra spaces and is generally not required.
        xml.setFormat(Format.getPrettyFormat());
        System.out.println(xml.outputString(jdomDoc));

    }
}
