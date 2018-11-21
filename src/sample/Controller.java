package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import sk.ditec.zep.dsigner.xades.XadesSig;
import sk.ditec.zep.dsigner.xades.plugin.DataObject;
import sk.ditec.zep.dsigner.xades.plugins.xmlplugin.XmlPlugin;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;


import static sample.Utils.appendTimeStampToFile;
import static sample.Utils.readResource;
import static sample.XMLOutputter.spracuj;
import static sample.XMLValidator.validate;

public class Controller implements Initializable, EventHandler<ActionEvent> {

    public TextField nameTextField, evidenceNumberTextField, dayCount, emailTextField, telephoneTextField, xmlTextField, xsdTextField;
    public TextField[] passengernameTextField = new TextField[4], passengerevidenceNumberTextField = new TextField[4], passengeremailTextField = new TextField[4], passengertelephoneTextField = new TextField[4];
    public DatePicker dateOfBirth;
    public DatePicker passengerDatePicker[] = new DatePicker[4];
    public Label label, priceInfo;
    public ChoiceBox brandChoice, typeChoice, modelChoice, motorChoice, colorChoice;
    public GridPane gridPane;
    public AnchorPane anchorPane;
    public Button addPassengerButton = new Button();

    Button nameButton = new Button();
    TextArea ta = new TextArea();
    String name = "";
    public DatePicker datePicker;

    Car car = new Car();
    Person customer = new Person();
    ArrayList<Person> passengers = new ArrayList<Person>();
    int rowCounter = 12;
    int passengersCounter = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (location.getFile().endsWith("sample.fxml")) {
            brandChoice.getItems().add("Škoda");
            brandChoice.getItems().add("VW");
            brandChoice.getItems().add("Toyota");
            brandChoice.getItems().add("Mercedes");

            typeChoice.getItems().add("hatchback");
            typeChoice.getItems().add("kombi");
            typeChoice.getItems().add("sedan");

            modelChoice.getItems().add("Octavia");
            modelChoice.getItems().add("Octavia Combi");
            modelChoice.getItems().add("Fabia");
            modelChoice.getItems().add("Passat");
            modelChoice.getItems().add("Golf");
            modelChoice.getItems().add("Golf Combi");
            modelChoice.getItems().add("Corolla");
            modelChoice.getItems().add("Land Cruiser");
            modelChoice.getItems().add("E-Classe");
            modelChoice.getItems().add("A-Classe");

            motorChoice.getItems().add("1.2 TSI 53 kW");
            motorChoice.getItems().add("1.6 TDI 83 kW");
            motorChoice.getItems().add("2.0 TDI 103kW");

            colorChoice.getItems().add("červená");
            colorChoice.getItems().add("zelená");
            colorChoice.getItems().add("modrá");

            dayCount.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue.equals("")) {
                        priceInfo.setText("0");
                        return;
                    }
                    priceInfo.setText(String.valueOf(Integer.valueOf(dayCount.getText()) * 10));

                }
            });
        }
    }

    @Override
    public void handle(ActionEvent event) {

        if (!validateForm()) {
            return;
        }

        customer.setName(nameTextField.getText());
        customer.setDateOfBirth(dateOfBirth.getValue());
        customer.setEvidenceNumber(evidenceNumberTextField.getText());
        customer.setEmail(emailTextField.getText());
        customer.setTelephoneNumber(telephoneTextField.getText());

        car.setBrand(brandChoice.getValue().toString());
        car.setType(typeChoice.getValue().toString());
        car.setModel(modelChoice.getValue().toString());
        car.setMotor(motorChoice.getValue().toString());
        car.setColor(colorChoice.getValue().toString());
        car.setDate(datePicker.getValue());
        car.setDayCount(dayCount.getText());
        car.setPrice(Integer.valueOf(priceInfo.getText()));

        for (int i = 0; i < passengersCounter; i++) {
            passengers.add(new Person());

            passengers.get(i).setName(passengernameTextField[i].getText());
            passengers.get(i).setDateOfBirth(passengerDatePicker[i].getValue());
            passengers.get(i).setEmail(passengeremailTextField[i].getText());
            passengers.get(i).setEvidenceNumber(passengerevidenceNumberTextField[i].getText());
            passengers.get(i).setTelephoneNumber(passengertelephoneTextField[i].getText());
        }

        spracuj(customer, car, passengers);
    }

    public void showValidateUI(ActionEvent event) throws Exception {

        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("validation.fxml"));
        primaryStage.setTitle("Validovanie");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.show();

    }

    public void setXMLPath(){

        FileChooser xml = new FileChooser();

        xmlTextField.setText(xml.showOpenDialog(null).getAbsolutePath());
    }

    public void setXSDParh(){

        FileChooser xml = new FileChooser();

        xsdTextField.setText(xml.showOpenDialog(null).getAbsolutePath());
    }

    public void validuj(ActionEvent event) {

        String xml = xmlTextField.getText();
        String xsd = xsdTextField.getText();

        label.setText(validate(xml, xsd));

        System.out.println(validate(xml, xsd));

    }


    public boolean validateForm() {

        if (nameTextField.getText().trim().isEmpty()) {
            nameTextField.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (emailTextField.getText().trim().isEmpty()) {
            emailTextField.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (evidenceNumberTextField.getText().trim().isEmpty()) {
            evidenceNumberTextField.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (telephoneTextField.getText().trim().isEmpty()) {
            telephoneTextField.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (brandChoice.getSelectionModel().isEmpty()) {
            brandChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (typeChoice.getSelectionModel().isEmpty()) {
            typeChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (modelChoice.getSelectionModel().isEmpty()) {
            modelChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (motorChoice.getSelectionModel().isEmpty()) {
            motorChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (colorChoice.getSelectionModel().isEmpty()) {
            colorChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (dayCount.getText().trim().isEmpty()) {
            dayCount.setStyle("-fx-background-color: #f44141");
            return false;
        }


        return true;
    }

    public void addPassenger() {

        anchorPane.setPrefHeight(anchorPane.getHeight() + 70);

        passengernameTextField[passengersCounter] = new TextField();
        passengerevidenceNumberTextField[passengersCounter] = new TextField();
        passengerDatePicker[passengersCounter] = new DatePicker();
        passengeremailTextField[passengersCounter] = new TextField();
        passengertelephoneTextField[passengersCounter] = new TextField();

        gridPane.add(new Label((passengersCounter + 1) + ". Pasažier"), 0, rowCounter++);

        gridPane.add(new Label("Meno a priezvisko"), 0, rowCounter);
        gridPane.add(passengernameTextField[passengersCounter], 1, rowCounter);
        gridPane.add(new Label("Email"), 2, rowCounter);
        gridPane.add(passengeremailTextField[passengersCounter], 3, rowCounter++);

        gridPane.add(new Label("Dátum narodenia"), 0, rowCounter);
        gridPane.add(passengerDatePicker[passengersCounter], 1, rowCounter);
        gridPane.add(new Label("Telefónne číslo"), 2, rowCounter);
        gridPane.add(passengertelephoneTextField[passengersCounter], 3, rowCounter++);

        gridPane.add(new Label("Číslo OP"), 0, rowCounter);
        gridPane.add(passengerevidenceNumberTextField[passengersCounter], 1, rowCounter++);


        if (++passengersCounter > 3) {
            addPassengerButton.setDisable(true);
        }
    }

    public void sign(ActionEvent actionEvent) {

        final XadesSig dSigner = new XadesSig();
        dSigner.installLookAndFeel();
        dSigner.installSwingLocalization();
        dSigner.reset();


        XmlPlugin xmlPlugin = new XmlPlugin();
        DataObject xmlObject;
        try {
            xmlObject = xmlPlugin.createObject2("car_rent",
                    "Car rent",
                    readResource("final.xml"),
                    readResource("car-rent.xsd"),
                    "",
                    "https://www.example.com/car-rent.xsd",
                    readResource("car-rent.xsl"),
                    "https://www.example.com/car-rent.xsl",
                    "HTML");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (xmlObject == null) {
            System.out.println("XMLPlugin.createObject() errorMessage=" + xmlPlugin.getErrorMessage());
            return;
        }

        int rc = dSigner.addObject(xmlObject);
        if (rc != 0) {
            System.out.println("XadesSig.addObject() errorCode=" + rc + ", errorMessage=" + dSigner.getErrorMessage());
            return;
        }

        rc = dSigner.sign("CAR_RENT_FIIT_STUBA",
                "http://www.w3.org/2001/04/xmlenc#sha256",
                "urn:oid:1.3.158.36061701.1.2.2", xadesSig -> {
                    if (xadesSig.getErrorMessage() != null) {
                        System.out.println("XadesSig.sign20() close=" + xadesSig.getErrorMessage());
                    }else {
                        try {
                            Utils.saveFile("signed-final.xml", xadesSig.getSignedXmlWithEnvelope());
                        } catch (IOException e) {
                            System.out.println("XadesSig.sign() problem saving signed file");
                            System.out.println("XadesSig.sing() : " + xadesSig.getSignedXmlWithEnvelope());
                            e.printStackTrace();
                        }
                    }
                });
        if (rc != 0) {
            System.out.println("XadesSig.sign() errorCode=" + rc + ", errorMessage=" + dSigner.getErrorMessage());
            return;
        }


    }

    public void show(ActionEvent actionEvent) {
        Utils.saveXMLtoHTML("final.xml", "final.html", "car-rent.xsl");
    }

    public void timeStamp(ActionEvent actionEvent) {
        Utils.appendTimeStampToFile("signed-final.xml", "timestamp-final.xml");
    }
}
