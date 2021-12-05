package com.mycompany.calculadora;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    @FXML
    void saveOperation(MouseEvent event) {
       Button button = ((Button) event.getSource());
       int value = Integer.parseInt(button.getText());
       System.out.println(value);
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    

}
