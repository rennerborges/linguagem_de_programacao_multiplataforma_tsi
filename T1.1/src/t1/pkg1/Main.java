/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t1.pkg1;

import java.util.ArrayList;

import t1.pkg1.controller.Endereco;
import t1.pkg1.controller.Telefone;
import t1.pkg1.controller.Cliente;
import t1.pkg1.controller.Fornecedor;

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
        Endereco enderecoCliente = new Endereco("Rua x", "Morrinhos");
        Telefone telefoneCliente1 = new Telefone("64", "99999999");
        Telefone telefoneCliente2 = new Telefone("44", "00000000");

        
        ArrayList<Telefone> telefonesCliente = new ArrayList();
        
        telefonesCliente.add(telefoneCliente1);
        telefonesCliente.add(telefoneCliente2);
        
        Cliente renner = new Cliente("Renner", "0000000000",telefonesCliente, enderecoCliente);
        
        renner.print();
        
        Endereco enderecoFornecedor = new Endereco("Rua y", "Caldas Novas");
        Telefone telefoneFornecedor1 = new Telefone("84", "84444444");
        Telefone telefoneFornecedor2 = new Telefone("11", "87444444");

        ArrayList<Telefone> telefonesFornecedor = new ArrayList();
        
        telefonesFornecedor.add(telefoneFornecedor1);
        telefonesFornecedor.add(telefoneFornecedor2);
        
        Fornecedor gisele = new Fornecedor("Gisele", "000000", telefonesFornecedor, enderecoFornecedor);
        
        gisele.print();
          

        
        
    }
    
}
