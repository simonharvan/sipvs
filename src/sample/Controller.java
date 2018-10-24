package sample;
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
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import sk.ditec.zep.dsigner.xades.XadesSig;
import sk.ditec.zep.dsigner.xades.plugin.DataObject;
import sk.ditec.zep.dsigner.xades.plugins.xmlplugin.XmlPlugin;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;


import static sample.Utils.readResource;
import static sample.XMLOutputter.spracuj;
import static sample.XMLValidator.validate;

public class Controller implements Initializable, EventHandler<ActionEvent> {

    public TextField nameTextField,  evidenceNumberTextField, dayCount, emailTextField, telephoneTextField, xmlTextField, xsdTextField;
    public TextField[] passengernameTextField = new TextField[4], passengerevidenceNumberTextField = new TextField[4], passengeremailTextField = new TextField[4], passengertelephoneTextField = new TextField[4];
    public DatePicker dateTextField;
    public DatePicker passengerDatePicker[] = new DatePicker[4];
    public Label label;
    public ChoiceBox brandChoice, typeChoice,modelChoice,motorChoice,colorChoice;
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
            brandChoice.getItems().add("Volkswagen");
            brandChoice.getItems().add("Renault");

            typeChoice.getItems().add("hatchback");
            typeChoice.getItems().add("kombi");
            typeChoice.getItems().add("sedan");

            modelChoice.getItems().add("Octavia");
            modelChoice.getItems().add("Scout");
            modelChoice.getItems().add("Fabia");

            motorChoice.getItems().add("1.2 TSI 53 kW");
            motorChoice.getItems().add("1.6 TDI 83 kW");
            motorChoice.getItems().add("2.0 TDI 103kW");

            colorChoice.getItems().add("červená");
            colorChoice.getItems().add("zelená");
            colorChoice.getItems().add("modrá");
        }
    }

    @Override
    public void handle(ActionEvent event) {

        if(!validateForm()){
            return;
        }

        customer.setName(nameTextField.getText());
        //customer.setDateOfBirth(dateTextField.getValue());
        customer.setEvidenceNumber(evidenceNumberTextField.getText());
        customer.setEmail(emailTextField.getText());
        customer.setTelephoneNumber(telephoneTextField.getText());

        car.setBrand(brandChoice.getValue().toString());
        car.setType(typeChoice.getValue().toString());
        car.setModel(modelChoice.getValue().toString());
        car.setMotor(motorChoice.getValue().toString());
        car.setColor(colorChoice.getValue().toString());
        //car.setDate(datePicker.getValue());
        car.setDayCount(dayCount.getText());

        for (int i = 0; i < passengersCounter; i++){
            passengers.add(new Person());

            passengers.get(i).setName(passengernameTextField[i].getText());
            passengers.get(i).setEmail(passengeremailTextField[i].getText());
            passengers.get(i).setEvidenceNumber(passengerevidenceNumberTextField[i].getText());
            passengers.get(i).setTelephoneNumber(passengertelephoneTextField[i].getText());
        }

        spracuj(customer, car, passengers);
    }

    public void showValidateUI(ActionEvent event) throws Exception{

        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("validation.fxml"));
        primaryStage.setTitle("Validovanie");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.show();

    }

    public void validuj(ActionEvent event) {

        String xml = xmlTextField.getText();
        String xsd = xsdTextField.getText();

        label.setText(validate(xml,xsd));

        System.out.println(validate(xml,xsd));

    }


    public boolean validateForm(){

        if (nameTextField.getText().trim().isEmpty()){
            nameTextField.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (emailTextField.getText().trim().isEmpty()){
            emailTextField.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (evidenceNumberTextField.getText().trim().isEmpty()){
            evidenceNumberTextField.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (telephoneTextField.getText().trim().isEmpty()){
            telephoneTextField.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (brandChoice.getSelectionModel().isEmpty()){
            brandChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (typeChoice.getSelectionModel().isEmpty()){
            typeChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (modelChoice.getSelectionModel().isEmpty()){
            modelChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (motorChoice.getSelectionModel().isEmpty()){
            motorChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (colorChoice.getSelectionModel().isEmpty()){
            colorChoice.setStyle("-fx-background-color: #f44141");
            return false;
        }

        if (dayCount.getText().trim().isEmpty()){
            dayCount.setStyle("-fx-background-color: #f44141");
            return false;
        }


        return true;
    }

    public void addPassenger(){

        anchorPane.setPrefHeight(anchorPane.getHeight() + 70);

        passengernameTextField[passengersCounter] = new TextField();
        passengerevidenceNumberTextField[passengersCounter] = new TextField();
        passengerDatePicker[passengersCounter] = new DatePicker();
        passengeremailTextField[passengersCounter] = new TextField();
        passengertelephoneTextField[passengersCounter] = new TextField();

        gridPane.add(new Label( (passengersCounter+1) + ". Pasažier"), 0, rowCounter++);

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


        if(++passengersCounter > 3) {
            addPassengerButton.setDisable(true);
        }
    }

    public void sign(ActionEvent actionEvent) {

        final XadesSig dSigner = new XadesSig();
        dSigner.installLookAndFeel();
        dSigner.installSwingLocalization();
        dSigner.reset();
        //dSigner.setLanguage("sk");

        XmlPlugin xmlPlugin = new XmlPlugin();
        DataObject xmlObject;
        try {
            xmlObject = xmlPlugin.createObject2("car_rent",
                    "Car rent",
                    readResource("car-rent.xml"),
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
                "urn:oid:1.3.158.36061701.1.2.2", xadesSig -> System.out.println("XadesSig.sign20() close=" + xadesSig.getErrorMessage()));
        if (rc != 0) {
            System.out.println("XadesSig.sign20() errorCode=" + rc + ", errorMessage=" + dSigner.getErrorMessage());
            return;
        }


        try {
            FileUtils.writeStringToFile(new File("signed-final.xml"), dSigner.getSignedXmlWithEnvelope(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(dSigner.getSignedXmlWithEnvelope());
    }

}
