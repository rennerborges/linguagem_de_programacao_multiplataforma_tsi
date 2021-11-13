/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Cliente;
import t1.pkg1.controller.Endereco;

/**
 *
 * @author renner
 */
public class ClienteDAO {

    public static void create(Cliente cliente) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO cliente (nome, cpf) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCpf());
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk_cliente = rs.getInt(1);
        cliente.setPk_cliente(pk_cliente);
        
        ArrayList<Endereco> enderecos = cliente.getEnderecos();
         
        if(enderecos != null) {
            for(Endereco endereco: enderecos){
                endereco.setFk_usuario(pk_cliente);
                
                ClienteEnderecoDAO.create(endereco);
            }
        }

        stm.close();
    }
    
    public static Cliente retrieve(int pk_cliente) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cliente WHERE pk_cliente ="+ pk_cliente);
        
        Cliente cliente = null;
        
        if(rs.next()){
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            
            ArrayList<Endereco> enderecos = ClienteEnderecoDAO.retrieveAllByCliente(pk_cliente);
            
            cliente = new Cliente(nome, cpf, enderecos);
        }
        
        return cliente;
    }
    
    public static ArrayList<Cliente> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cliente");
        
        ArrayList<Cliente> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk_cliente = rs.getInt("pk_cliente");
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            
            ArrayList<Endereco> enderecos = ClienteEnderecoDAO.retrieveAllByCliente(pk_cliente);
            
            aux.add(new Cliente(nome, cpf, enderecos));
        }
        
        return aux;
    }
    
    public static void delete(int pk_cliente) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM cliente WHERE pk_cliente ="+ pk_cliente);

    }
    
    public static void update(Cliente cliente) throws SQLException{
         Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE cliente SET nome = ?, cpf = ? WHERE pk_cliente= ?"
        );
        
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCpf());
        stm.setInt(3, cliente.getPk_cliente());

        stm.execute();
        
        stm.close();
    }
}
