package com.mycompany.calculadora;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrimaryController {
    
    private String numero1 = "";
    private String numero2 = "";
    private String operation;
    
    @FXML
    private TextField visor;
    
    @FXML
    void saveNumber(MouseEvent event) {
        
       Button button = ((Button) event.getSource());
       int value = Integer.parseInt(button.getText());
       
       if(operation == null){
           numero1 += button.getText();
       }else{
           numero2 += button.getText(); 
       }
       
       prepareVisor();

       
    }
    
    @FXML
    void saveOperation(MouseEvent event) {
        Button button = ((Button) event.getSource());
       
        operation = button.getText();
       
        prepareVisor();

    }
    
    @FXML
    void clearLastOperation() {
        boolean verificado = false;
        
        if(!numero2.equals("")){
            numero2  = numero2.substring(0, numero2.length()-1);
            verificado = true;
        }
        
        if(operation != null && !verificado){
            operation = null; 
            verificado = true;
        }
        
        if(!verificado){
            numero1  = numero1.substring(0, numero1.length()-1);

        }
        
        prepareVisor();
        
        
    }
    
    private void prepareVisor(){
        
        String operationString = operation != null ? operation : "";
        
        visor.setText(numero1 + " " + operationString + " " + numero2);
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    

}
