package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.Date;

import static sample.XMLOutputter.spracuj;

public class Controller implements EventHandler<ActionEvent> {

    public TextField nameTextField, evidenceNumberTextField, emailTextField, telephoneTextField;
    public DatePicker dateOfBirth;

    public ChoiceBox brandChoice, typeChoice, modelChoice, motorChoice, colorChoice;
    public DatePicker datePicker;
    public TextField dayCount;

    public Label priceInfo;

    Car car = new Car();
    Person customer = new Person();

    @Override
    public void handle(ActionEvent event) {

        customer.setName(nameTextField.getText());
        customer.setDateOfBirth(dateOfBirth.getValue());
        customer.setEvidenceNumber(evidenceNumberTextField.getText());
        customer.setEmail(emailTextField.getText());
        customer.setTelephoneNumber(telephoneTextField.getText());

        /*
        car.setBrand(brandChoice.getValue().toString());
        car.setType(typeChoice.getValue().toString());
        car.setModel(modelChoice.getValue().toString());
        car.setMotor(motorChoice.getValue().toString());
        car.setColor(colorChoice.getValue().toString());
        car.setDate(datePicker.getValue());
        car.setDayCount(dayCount.getText());
        */

        spracuj(customer, car);
    }

}
