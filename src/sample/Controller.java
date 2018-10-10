package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;

import static sample.XMLOutputter.spracuj;
import static sample.XMLValidator.validate;

public class Controller implements EventHandler<ActionEvent> {

    public TextField nameTextField, evidenceNumberTextField, emailTextField, telephoneTextField, xmlTextField, xsdTextField;
    public Date dateTextField;
    public Label label;
    Button nameButton = new Button();
    TextArea ta = new TextArea();
    String name = "";

    Car car = new Car();
    Person customer = new Person();

    @Override
    public void handle(ActionEvent event) {

        // TAKTO VIEME VYTIAHNUT TEXT Z TEXTFIELDU, TOTO POTREBUJEME DOSTAT
        // DO TRIEDY XMLOutputter A Z TYCHTO DAT SPRAVIT XML

        name=nameTextField.getText();

        customer.setName(nameTextField.getText());
        customer.setDateOfBirth(dateTextField);
        System.out.println(customer.getDateOfBirth());
        customer.setEvidenceNumber(evidenceNumberTextField.getText());
        customer.setEmail(emailTextField.getText());
        customer.setTelephoneNumber(telephoneTextField.getText());

        spracuj(customer);
    }

    public void showValidateUI(ActionEvent event) throws Exception{

        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("validation.fxml"));
        primaryStage.setTitle("Validovanie");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.show();

    }

    public void validuj() {

        String xml = xmlTextField.getText();
        String xsd = xsdTextField.getText();

        label.setText(validate(xml,xsd));

        System.out.println(validate(xml,xsd));

    }
}
