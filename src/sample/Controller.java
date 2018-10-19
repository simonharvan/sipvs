package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


import static sample.XMLOutputter.spracuj;
import static sample.XMLValidator.validate;

public class Controller implements Initializable, EventHandler<ActionEvent> {

    public TextField nameTextField,  evidenceNumberTextField, dayCount, emailTextField, telephoneTextField, xmlTextField, xsdTextField;
    public DatePicker dateTextField;
    public Label label;
    public ChoiceBox brandChoice, typeChoice,modelChoice,motorChoice,colorChoice;

    Button nameButton = new Button();
    TextArea ta = new TextArea();
    String name = "";
    public DatePicker datePicker;

    Car car = new Car();
    Person customer = new Person();

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

        spracuj(customer, car);
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
}
