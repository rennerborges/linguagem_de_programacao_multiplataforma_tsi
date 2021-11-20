/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Fornecedor;
import t1.pkg1.controller.Endereco;
/**
 *
 * @author renner
 */
public class FornecedorDAO {
    public static void create(Fornecedor fornecedor) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO fornecedor (nome, cpf) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        stm.setString(1, fornecedor.getNome());
        stm.setString(2, fornecedor.getCpf());
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk = rs.getInt(1);
        fornecedor.setPk(pk);
        
        ArrayList<Endereco> enderecos = fornecedor.getEnderecos();
         
        if(enderecos != null) {
            for(Endereco endereco: enderecos){
                endereco.setFk_usuario(pk);
                
                FornecedorEnderecoDAO.create(endereco);
            }
        }

        stm.close();
    }
    
    public static Fornecedor retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor WHERE pk_fornecedor ="+ pk);
        
        Fornecedor fornecedor = null;
        
        if(rs.next()){
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            
            ArrayList<Endereco> enderecos = FornecedorEnderecoDAO.retrieveAllByFornecedor(pk);
            
            fornecedor = new Fornecedor(nome, cpf, enderecos, pk);
        }
        
        return fornecedor;
    }    
    
    public static Fornecedor retrieve(Fornecedor fornecedor) throws SQLException{
        return retrieve(fornecedor.getPk());
    }
 
    public static ArrayList<Fornecedor> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fornecedor");
        
        ArrayList<Fornecedor> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_fornecedor");
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            
            ArrayList<Endereco> enderecos = FornecedorEnderecoDAO.retrieveAllByFornecedor(pk);
            
            aux.add(new Fornecedor(nome, cpf, enderecos, pk));
        }
        
        return aux;
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM fornecedor WHERE pk_fornecedor="+ pk);

    }
    
    public static void delete(Fornecedor fornecedor) throws SQLException{
        delete(fornecedor.getPk());
    }
    
    public static void update(Fornecedor fornecedor) throws SQLException{
         Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE fornecedor SET nome = ?, cpf = ? WHERE pk_fornecedor= ?"
        );
        
        stm.setString(1, fornecedor.getNome());
        stm.setString(2, fornecedor.getCpf());
        stm.setInt(3, fornecedor.getPk());

        stm.execute();
        
        stm.close();
        
        ArrayList<Endereco> enderecos = fornecedor.getEnderecos();
        
        //Percorrendo o array de endereços verificando se existe para atualizar
        //Ou se é para ser criado        
        
        for(int i = 0; i < enderecos.size(); i++ ){
            Endereco endereco = fornecedor.getEnderecos().get(i);
            
            if(endereco.getStatus() == Endereco.EXCLUIDO){
                FornecedorEnderecoDAO.delete(endereco);
                fornecedor.getEnderecos().remove(endereco);
                i--;
                continue;
            }
            
            if(endereco.getPk_endereco() == 0){
                endereco.setFk_usuario(fornecedor.getPk());
                FornecedorEnderecoDAO.create(endereco);
                continue;
            }
            
            FornecedorEnderecoDAO.update(endereco);
        }
        
    }
    
}
