package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static sample.XMLOutputter.spracuj;

public class Controller implements EventHandler<ActionEvent> {

    public TextField nameTextField;
    Button nameButton = new Button();
    TextArea ta = new TextArea();
    String name = "";

    @Override
    public void handle(ActionEvent event) {

        // TAKTO VIEME VYTIAHNUT TEXT Z TEXTFIELDU, TOTO POTREBUJEME DOSTAT
        // DO TRIEDY XMLOutputter A Z TYCHTO DAT SPRAVIT XML

        name=nameTextField.getText();
        spracuj(name);
    }
}
