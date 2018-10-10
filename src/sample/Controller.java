package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Date;

import static sample.XMLOutputter.spracuj;

public class Controller implements EventHandler<ActionEvent> {

    public TextField nameTextField, evidenceNumberTextField, emailTextField, telephoneTextField;
    public Date dateTextField;
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
}
