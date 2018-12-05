package sample.verify;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VerificationController implements Initializable, EventHandler<ActionEvent> {


    public Button setFilesButton;
    public TextArea text;
    private List<File> fileList = new ArrayList<>();
    private List<Verificator> verificators = new ArrayList<>();

    @Override
    public void handle(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verificators.add(new DataEnvelopeVerificator());
        verificators.add(new SignatureElementVerificator());
        verificators.add(new SignatureValueVerificator());
    }

    public void setXMLsPath(ActionEvent mouseEvent) {
        FileChooser xml = new FileChooser();

        fileList = xml.showOpenMultipleDialog(null);
        setFilesButton.setText("Good to go");
    }

    public void verify(ActionEvent actionEvent) {
        for (File file : fileList) {
            text.setText(text.getText() + file.getName() + "\n");
            for (Verificator verificator : verificators) {
                verificator.verify(file, (good, response) -> {
                    text.setText(text.getText() + String.valueOf(good).toUpperCase() + "\t" + response + "\n");
                });
            }
            text.setText(text.getText() + "\n\n");
        }
    }
}
