/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

import java.util.ArrayList;
import t1.pkg1.controller.Produto;

/**
 *
 * @author renner
 */
public class ProdutoDAO {
    public static void create(Produto produto) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO produto (nome, estoque_minimo, qtd_estoque) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        stm.setString(1, produto.getNome());
        stm.setInt(2, produto.getEstoqueMinimo());
        stm.setInt(3, produto.getQtdEstoque());
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk = rs.getInt(1);
        produto.setPk(pk);
    }
    
    public static Produto retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM produto WHERE pk_produto ="+ pk);
        
        Produto produto = null;
        
        if(rs.next()){
            String nome = rs.getString("nome");
            int estoqueMinimo = rs.getInt("estoque_minimo");
            int qtdEstoque = rs.getInt("qtd_estoque");
                        
            produto = new Produto(pk, nome, estoqueMinimo, qtdEstoque);
        }
        
        return produto;

    }
    
    public static Produto retrieve(Produto produto) throws SQLException{
        return retrieve(produto.getPk());
    }
    
    public static ArrayList<Produto> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM produto");
        
        ArrayList<Produto> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_produto");
            String nome = rs.getString("nome");
            int estoqueMinimo = rs.getInt("estoque_minimo");
            int qtdEstoque = rs.getInt("qtd_estoque");
                        
            aux.add(new Produto(pk, nome, estoqueMinimo, qtdEstoque));
        }
        
        return aux;
    }
    
    public static void update(Produto produto) throws SQLException{
         Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE produto SET nome = ?, estoque_minimo = ?, qtd_estoque = ? WHERE pk_produto = ?"
        );
        
        stm.setString(1, produto.getNome());
        stm.setInt(2, produto.getEstoqueMinimo());        
        stm.setInt(3, produto.getQtdEstoque());        
        stm.setInt(4, produto.getPk());

        stm.execute();
        
        stm.close();        
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM produto WHERE pk_produto="+ pk);
    }
    
    public static void delete(Produto produto) throws SQLException{
        delete(produto.getPk());
    }
}
