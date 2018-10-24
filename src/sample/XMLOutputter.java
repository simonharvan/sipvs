package sample;

import javafx.scene.effect.PerspectiveTransform;
import org.jdom2.*;
import org.jdom2.output.Format;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XMLOutputter {

    public static void spracuj(Person customer, Car customerCar, ArrayList<Person> passengersArrayList){

        try {

            // create the jdom
            Document jdomDoc = new Document();
            // create root element
            Element rootElement = new Element("car-rent");
            rootElement.setAttribute("orderid", String.valueOf(Math.round(Math.random()*100000)));
            //rootElement.setAttribute("xmlns/:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            //rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "car-rent.xsd");
            jdomDoc.setRootElement(rootElement);



            // add child
            Element orderPerson = new Element("orderperson");

                Element name = new Element("name");
                name.addContent(customer.getName());
                orderPerson.addContent(name);

                Element date = new Element("date-of-birth");
                //date.addContent(customer.getDateOfBirth().toString());
                date.addContent(customer.getDateOfBirth().toString());
                orderPerson.addContent(date);

                Element id = new Element("id");
                id.addContent(customer.getEvidenceNumber());
                orderPerson.addContent(id);

                Element email = new Element("email");
                email.addContent(customer.getEmail());
                orderPerson.addContent(email);

                Element phone = new Element("phone-number");
                phone.addContent(customer.getTelephoneNumber());
                orderPerson.addContent(phone);

            rootElement.addContent(orderPerson);

            Element passengers = new Element("passengers");
            for (Person passenger : passengersArrayList){

                Element passPerson = new Element("person");
                passengers.addContent(passPerson);

                    Element passName = new Element("name");
                    passName.addContent(passenger.getName());
                    passPerson.addContent(passName);

                    Element passDate = new Element("date-of-birth");
                    passDate.addContent(passenger.getDateOfBirth().toString());
                    passPerson.addContent(passDate);

                    Element passId = new Element("id");
                    passId.addContent(passenger.getEvidenceNumber());
                    passPerson.addContent(passId);

                    Element passEmail = new Element("email");
                    passEmail.addContent(passenger.getEmail());
                    passPerson.addContent(passEmail);

                    Element passPhone = new Element("phone-number");
                    passPhone.addContent(passenger.getTelephoneNumber());
                    passPerson.addContent(passPhone);
            }

            rootElement.addContent(passengers);


            Element car = new Element("car");

                Element brand = new Element("brand");
                brand.addContent(customerCar.getBrand());
                car.addContent(brand);

                Element type = new Element("type");
                type.addContent(customerCar.getType());
                car.addContent(type);

                Element model = new Element("model");
                model.addContent(customerCar.getModel());
                car.addContent(model);

                Element engine = new Element("engine");
                engine.addContent(customerCar.getMotor());
                car.addContent(engine);

                Element color = new Element("color");
                color.addContent(customerCar.getColor());
                car.addContent(color);


                Element dateRent = new Element("date");
                dateRent.addContent(customerCar.getDate().toString());
                car.addContent(dateRent);

                Element numberOfDays = new Element("number-of-days");
                numberOfDays.addContent(customerCar.getDayCount());
                car.addContent(numberOfDays);

                Element price = new Element("price");
                price.addContent(String.valueOf(customerCar.getPrice()));
                car.addContent(price);

            rootElement.addContent(car);



        // add attributes
        //Attribute nameAttr = new Attribute("name", "John Smith");
        //orderPerson.setAttribute(nameAttr);

        // Output as XML
        // create XMLOutputter
        org.jdom2.output.XMLOutputter xml = new org.jdom2.output.XMLOutputter();
        // we want to format the xml. This is used only for demonstration. pretty formatting adds extra spaces and is generally not required.
        xml.setFormat(Format.getPrettyFormat());
        System.out.println(xml.outputString(jdomDoc));

        xml.output(jdomDoc, new FileWriter("final.xml"));

        System.out.println("File Saved!");
    } catch (IOException io) {
        System.out.println(io.getMessage());
    }

    }
}
