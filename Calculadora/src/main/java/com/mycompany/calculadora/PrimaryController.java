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
       
       if(operation == null){
           numero1 += button.getText();
       }else{
           numero2 += button.getText(); 
       }
       
       prepareVisor();

       
    }
    
    @FXML
    void saveOperation(MouseEvent event) {
        
        if(!numero1.equals("") && !numero2.equals("") && operation != null){
            resolve();
        }
        
        if(numero1.equals("")){
            return;
        }
        
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
        
        if(!verificado && !numero1.equals("")){
            numero1  = numero1.substring(0, numero1.length()-1);

        }
        
        prepareVisor();
        
        
    }
    
    @FXML
    void clearAll() {
        numero1 = "";
        numero2 = "";
        operation = null;
        prepareVisor();

    }
    
    @FXML
    void resolve() {
        float n1 = Float.parseFloat(numero1);
        float n2 = Float.parseFloat(numero2);
        
        float resultado = 0.0f;
        
        if(operation.equals("+")){
            resultado = n1 + n2;
        }
        
        if(operation.equals("-")){
            resultado = n1 - n2;
        }
        
        if(operation.equals("/")){
            resultado = n1 / n2;
        }
        
        if(operation.equals("X")){
            System.out.println(n1);
            System.out.println(n2);

            resultado = n1 * n2;
        }
        
        operation = null;
        numero1 = Float.toString(resultado);
        numero2 = "";
        
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
