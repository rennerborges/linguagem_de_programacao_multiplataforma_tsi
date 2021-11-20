/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Cargo;
import t1.pkg1.controller.Funcionario;
import t1.pkg1.controller.Endereco;
/**
 *
 * @author renner
 */
public class FuncionarioDAO {
    public static void create(Funcionario funcionario) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO funcionario (nome, cpf, fk_cargo) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        Cargo cargo = funcionario.getCargo();
        
        if(cargo.getPk_cargo() == 0){
            CargoDAO.create(cargo);
        }
        
        stm.setString(1, funcionario.getNome());
        stm.setString(2, funcionario.getCpf());
        stm.setInt(3, cargo.getPk_cargo());
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk = rs.getInt(1);
        funcionario.setPk(pk);
        
        ArrayList<Endereco> enderecos = funcionario.getEnderecos();
         
        if(enderecos != null) {
            for(Endereco endereco: enderecos){
                endereco.setFk_usuario(pk);
                
                FuncionarioEnderecoDAO.create(endereco);
            }
        }

        stm.close();
    }
    
    public static Funcionario retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM funcionario WHERE pk_funcionario ="+ pk);
        
        Funcionario funcionario = null;
        
        if(rs.next()){
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            
            Cargo cargo = CargoDAO.retreave(rs.getInt("fk_cargo"));
            
            ArrayList<Endereco> enderecos = FuncionarioEnderecoDAO.retrieveAllByFuncionario(pk);
            
            funcionario = new Funcionario(nome, cpf, enderecos, cargo, pk);
        }
        
        return funcionario;
    }
    
    public static Funcionario retrieve(Funcionario funcionario) throws SQLException{
        return retrieve(funcionario.getPk());
    }
    
    public static ArrayList<Funcionario> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM funcionario");
        
        ArrayList<Funcionario> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_funcionario");
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            
            Cargo cargo = CargoDAO.retreave(rs.getInt("fk_cargo"));
            
            ArrayList<Endereco> enderecos = FuncionarioEnderecoDAO.retrieveAllByFuncionario(pk);
            
            aux.add(new Funcionario(nome, cpf, enderecos, cargo, pk));
        }
        
        return aux;
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM funcionario WHERE pk_funcionario="+ pk);

    }
    
    public static void delete(Funcionario funcionario) throws SQLException{
        delete(funcionario.getPk());
    }
    
    public static void update(Funcionario funcionario) throws SQLException{
         Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE funcionario SET nome = ?, cpf = ? WHERE pk_funcionario= ?"
        );
        
        stm.setString(1, funcionario.getNome());
        stm.setString(2, funcionario.getCpf());
        stm.setInt(3, funcionario.getPk());

        stm.execute();
        
        stm.close();
        
        ArrayList<Endereco> enderecos = funcionario.getEnderecos();
        
        //Percorrendo o array de endereços verificando se existe para atualizar
        //Ou se é para ser criado        
        
        for(int i = 0; i < enderecos.size(); i++ ){
            Endereco endereco = funcionario.getEnderecos().get(i);
            
            if(endereco.getStatus() == Endereco.EXCLUIDO){
                FuncionarioEnderecoDAO.delete(endereco);
                funcionario.getEnderecos().remove(endereco);
                i--;
                continue;
            }
            
            if(endereco.getPk_endereco() == 0){
                endereco.setFk_usuario(funcionario.getPk());
                FuncionarioEnderecoDAO.create(endereco);
                continue;
            }
            
            FuncionarioEnderecoDAO.update(endereco);
        }
        
    }
}
