/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Endereco;

/**
 *
 * @author renner
 */
public class FornecedorEnderecoDAO {
    public static void create(Endereco endereco) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO fornecedor_endereco (fk_fornecedor, logradouro, bairro, cidade, estado, pais, cep) VALUES (?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        stm.setInt(1, endereco.getFk_usuario());
        stm.setString(2, endereco.getLogradouro());
        stm.setString(3, endereco.getBairro());
        stm.setString(4, endereco.getCidade());
        stm.setString(5, endereco.getEstado());
        stm.setString(6, endereco.getPais());
        stm.setString(7, endereco.getCep());

        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk_endereco = rs.getInt(1);
        endereco.setPk_endereco(pk_endereco);
        
        stm.close();
        
    }
    
    public static Endereco retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor_endereco WHERE pk_endereco =" + pk);
        
        Endereco endereco = null;
        
        if(rs.next()){
            int fk_fornecedor = rs.getInt("fk_fornecedor");
            String logradouro = rs.getString("logradouro");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String pais = rs.getString("pais");
            String cep = rs.getString("cep");
            
            endereco = new Endereco(pk, fk_fornecedor, logradouro, bairro, cidade, estado, pais, cep);
        }
            
        return endereco;
    }
    
    public static Endereco retrieve(Endereco endereco) throws SQLException{
        return retrieve(endereco.getPk_endereco());
    }
    
    public static ArrayList<Endereco> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor_endereco ORDER BY pk_endereco");
        
        ArrayList<Endereco> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk_endereco = rs.getInt("pk_endereco");
            int fk_fornecedor = rs.getInt("fk_fornecedor");
            String logradouro = rs.getString("logradouro");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String pais = rs.getString("pais");
            String cep = rs.getString("cep");
            
            aux.add(new Endereco(pk_endereco, fk_fornecedor, logradouro, bairro, cidade, estado, pais, cep));
        }
            
        return aux;
    }
    
    public static ArrayList<Endereco> retrieveAllByFornecedor(int fk_fornecedor) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor_endereco WHERE fk_fornecedor ="+ fk_fornecedor + "ORDER BY pk_endereco");
        
        ArrayList<Endereco> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk_endereco = rs.getInt("pk_endereco");
            String logradouro = rs.getString("logradouro");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String pais = rs.getString("pais");
            String cep = rs.getString("cep");
            
            aux.add(new Endereco(pk_endereco, fk_fornecedor, logradouro, bairro, cidade, estado, pais, cep));
        }
            
        return aux;
    }
    
    public static void update(Endereco endereco) throws SQLException{
        
        if(endereco.getStatus() != Endereco.ALTERADO){
            return;
        }
       
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE fornecedor_endereco SET fk_fornecedor = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, cep = ? WHERE pk_endereco = ?"
        );
         
        stm.setInt(1, endereco.getFk_usuario());
        stm.setString(2, endereco.getLogradouro());
        stm.setString(3, endereco.getBairro());
        stm.setString(4, endereco.getCidade());
        stm.setString(5, endereco.getEstado());
        stm.setString(6, endereco.getPais());
        stm.setString(7, endereco.getCep());
        stm.setInt(8, endereco.getPk_endereco());

        stm.execute();
        
        stm.close();
        
        endereco.resetStatus();
    }
    
    
    public static void delete(int pk_endereco) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM fornecedor_endereco WHERE pk_endereco="+ pk_endereco);
    }
    
    public static void delete(Endereco endereco) throws SQLException{
        delete(endereco.getPk_endereco());
    }
}
