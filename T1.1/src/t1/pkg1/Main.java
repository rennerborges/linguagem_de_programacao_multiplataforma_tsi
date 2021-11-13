/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package t1.pkg1;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import t1.pkg1.controller.Endereco;
import t1.pkg1.controller.Telefone;
import t1.pkg1.controller.Cliente;
import t1.pkg1.controller.Fornecedor;

import java.sql.*;
import model.BancoDados;
import model.CargoDAO;
import model.ClienteDAO;
import model.ClienteEnderecoDAO;

import model.DBString;
import t1.pkg1.controller.Cargo;
/**
 *
 * @author renner
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        Endereco endereco1 = new Endereco("Rua da saudade", "Jardim América", "Morrinhos", "GO", "Brazil", "75650000");
        Endereco endereco2 = new Endereco("Alameda sul", "Setor aeroporto", "Morrinhos", "GO", "Brazil", "75650000");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco1);
        enderecos.add(endereco2);

//        Cliente cliente = new Cliente("Gisele Santos", "99999999999", enderecos, 6);
//        ClienteDAO.update(cliente);
//
//        ClienteDAO.delete(4);

        Cliente recuperado = ClienteDAO.retrieve(6);
        System.out.println(recuperado);
        ArrayList<Endereco> enderecoRecuperado = recuperado.getEnderecos();
        Endereco endereco3 = new Endereco("Rua x", "Alameda B", "Goiania", "GO", "Brazil", "75650000");
        enderecoRecuperado.add(endereco3);
        
        enderecoRecuperado.get(1).setBairro("OLOKINHO");
        
        recuperado.setEnderecos(enderecoRecuperado);
//        ClienteDAO.update(recuperado);
        
//        gisele.print();

//        Connection conn = BancoDados.createConnection();
//        conn.createStatement().execute("INSERT INTO CARGO (NOME) VALUES ('Java senior')");

//        Cargo c1 = new Cargo("Gerente de TI2", "Gerencia o TI");
//
//        CargoDAO.create(c1);
//
//        System.out.println(c1.getPk_cargo());

//        for(Cargo aux: CargoDAO.retreaveAll()){
//            System.out.println(aux);
//        }

//        System.out.println(CargoDAO.retreave(1));
           
//        Cargo x = CargoDAO.retreave(4);
//        x.setNome("Secretário Geral da Onu");
//        CargoDAO.update(x);                   
    }
    
}
