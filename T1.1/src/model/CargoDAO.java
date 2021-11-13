/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
import java.util.ArrayList;

import t1.pkg1.controller.Cargo;

/**
 *
 * @author renner
 */
public class CargoDAO {
    
    /**
     * Insere uma linha na tabela de cargo realizando o MOR de cargo
     * @param c
     * @throws SQLException 
     */
    
    public static void create(Cargo c) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement("INSERT INTO CARGO (NOME, DESCRICAO) VALUES (?,?)");
        stm.setString(1, c.getNome());
        stm.setString(2, c.getDescricao());
        stm.execute();
        
        stm.close();

        ResultSet pks = stm.getGeneratedKeys();
        
        pks.next();
        
        int pkGerado = pks.getInt(1); 
        
        c.setPk_cargo(pkGerado);

    }
    
    public static Cargo retreave(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cargo WHERE pk_cargo=" + pk);
        
        Cargo c = null;
        
        if(rs.next()){
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");
            int pk_cargo = rs.getInt("pk_cargo");
        
            c = new Cargo(nome, descricao, pk_cargo);
        }
        
        return c;
    }
    
    /**
     * Retornar todos os cargos armazenados no banco de dados
     * @return 
     */
    
    public static ArrayList<Cargo> retreaveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cargo ORDER BY nome");
        
        ArrayList<Cargo> aux = new ArrayList<>();
        
        while(rs.next()){
            String nome = rs.getString("nome");
            String descricao = rs.getString("descricao");
            int pk_cargo = rs.getInt("pk_cargo");
            
            Cargo c = new Cargo(nome, descricao, pk_cargo);
            
            aux.add(c);
        }

        return aux;
    }
}
