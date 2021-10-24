/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t1.pkg1;

import java.util.ArrayList;

import t1.pkg1.controller.Endereco;
import t1.pkg1.controller.Telefone;
import t1.pkg1.controller.Cliente;

/**
 *
 * @author renner
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Endereco endereco = new Endereco("Rua x", "Morrinhos");
        Telefone telefone1 = new Telefone("64", "99999999");
        Telefone telefone2 = new Telefone("44", "00000000");

        
        ArrayList<Telefone> telefones = new ArrayList();
        
        telefones.add(telefone1);
        telefones.add(telefone2);
        
        Cliente renner = new Cliente("Renner", "0000000000",telefones, endereco);
        
        renner.print();
          

        
        
    }
    
}
