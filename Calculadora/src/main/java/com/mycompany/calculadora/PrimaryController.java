package com.mycompany.calculadora;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrimaryController {
    
    @FXML
    private TextField visor;
    
    @FXML
    void saveNumber(MouseEvent event) {
       Button button = ((Button) event.getSource());
       int value = Integer.parseInt(button.getText());
       visor.setText(button.getText());
    }
    
    @FXML
    void saveOperation(MouseEvent event) {
       Button button = ((Button) event.getSource());
       visor.setText(button.getText());
    }
    
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    

}
