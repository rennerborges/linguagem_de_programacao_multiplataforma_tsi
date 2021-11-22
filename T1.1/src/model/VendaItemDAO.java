/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Produto;
import t1.pkg1.controller.Item;

/**
 *
 * @author renner
 */
public class VendaItemDAO {
    public static void create(Item vendaItem) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        if(vendaItem.getProduto().getPk() == 0){
            ProdutoDAO.create(vendaItem.getProduto());
        }
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO venda_item (fk_venda, fk_produto, qtd, valor_unitario) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        stm.setInt(1, vendaItem.getFk());
        stm.setInt(2, vendaItem.getProduto().getPk());
        stm.setInt(3, vendaItem.getQtd());
        stm.setFloat(4, vendaItem.getValorUnitario());

        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk = rs.getInt(1);
        vendaItem.setPk(pk);
        
        stm.close();
        
    }
    
    public static Item retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda_item WHERE pk_item =" + pk);
        
        Item vendaItem = null;
        
        if(rs.next()){
            int fkVenda = rs.getInt("fk_venda");
            int fkProduto = rs.getInt("fk_produto");
            int qtd = rs.getInt("qtd");
            float valorUnitario = rs.getFloat("valor_unitario");
            
            Produto produto = ProdutoDAO.retrieve(fkProduto);
        
            vendaItem = new Item(pk, fkVenda, produto, qtd, valorUnitario);
        }
            
        return vendaItem;
    }
    
    public static Item retrieve(Item vendaItem) throws SQLException{
        return retrieve(vendaItem.getPk());
    }
    
    public static ArrayList<Item> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda_item ORDER BY pk_item");
        
        ArrayList<Item> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_item");
            int fkVenda = rs.getInt("fk_venda");
            int fkProduto = rs.getInt("fk_produto");
            int qtd = rs.getInt("qtd");
            float valorUnitario = rs.getFloat("valor_unitario");
            
            Produto produto = ProdutoDAO.retrieve(fkProduto);
 
            aux.add(new Item(pk, fkVenda, produto, qtd, valorUnitario));
        }
            
        return aux;
    }
    
    public static ArrayList<Item> retrieveAllByVenda(int fk_venda) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda_item WHERE fk_venda ="+ fk_venda + "ORDER BY pk_item");
        
        ArrayList<Item> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_item");
            int fkVenda = rs.getInt("fk_venda");
            int fkProduto = rs.getInt("fk_produto");
            int qtd = rs.getInt("qtd");
            float valorUnitario = rs.getFloat("valor_unitario");
            
            Produto produto = ProdutoDAO.retrieve(fkProduto);
 
            aux.add(new Item(pk, fkVenda, produto, qtd, valorUnitario));
        }
            
        return aux;
    }
    
    public static void update(Item compraItem) throws SQLException{
        
        if(compraItem.getStatus() != Item.ALTERADO){
            return;
        }
       
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE venda_item SET fk_venda = ?, fk_produto = ?, qtd = ?, valor_unitario = ? WHERE pk_item = ?"
        );
         
        stm.setInt(1, compraItem.getFk());
        stm.setInt(2, compraItem.getProduto().getPk());
        stm.setInt(3, compraItem.getQtd());
        stm.setFloat(4, compraItem.getValorUnitario());
        stm.setInt(5, compraItem.getPk());

        stm.execute();
        
        stm.close();
        
        compraItem.resetStatus();
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM venda_item WHERE pk_item="+ pk);
    }
    
    public static void delete(Item vendaItem) throws SQLException{
        delete(vendaItem.getPk());
    }
    
    public static void deleteAllByVenda(int fk_venda) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM venda_item WHERE fk_venda ="+ fk_venda);
    }
}
