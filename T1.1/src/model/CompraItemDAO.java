/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Produto;
import t1.pkg1.controller.CompraItem;

/**
 *
 * @author renner
 */
public class CompraItemDAO {
    public static void create(CompraItem compraItem) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO compra_item (fk_compra, fk_produto, qtd, valor_unitario) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        stm.setInt(1, compraItem.getFkCompra());
        stm.setInt(2, compraItem.getProduto().getPk());
        stm.setInt(3, compraItem.getQtd());
        stm.setFloat(4, compraItem.getValorUnitario());

        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk = rs.getInt(1);
        compraItem.setPk(pk);
        
        stm.close();
        
    }
    
    public static CompraItem retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM compra_item WHERE pk_item =" + pk);
        
        CompraItem compraItem = null;
        
        if(rs.next()){
            int fkCompra = rs.getInt("fk_compra");
            int fkProduto = rs.getInt("fk_produto");
            int qtd = rs.getInt("qtd");
            float valorUnitario = rs.getFloat("valor_unitario");
            
            Produto produto = ProdutoDAO.retrieve(fkProduto);
        
            compraItem = new CompraItem(pk, fkCompra, produto, qtd, valorUnitario);
        }
            
        return compraItem;
    }
    
    public static CompraItem retrieve(CompraItem compraItem) throws SQLException{
        return retrieve(compraItem.getPk());
    }
    
    public static ArrayList<CompraItem> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM compra_item ORDER BY pk_item");
        
        ArrayList<CompraItem> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_item");
            int fkCompra = rs.getInt("fk_compra");
            int fkProduto = rs.getInt("fk_produto");
            int qtd = rs.getInt("qtd");
            float valorUnitario = rs.getFloat("valor_unitario");
            
            Produto produto = ProdutoDAO.retrieve(fkProduto);
 
            aux.add(new CompraItem(pk, fkCompra, produto, qtd, valorUnitario));
        }
            
        return aux;
    }
    
    public static ArrayList<CompraItem> retrieveAllByCompra(int fk_compra) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM compra_item WHERE fk_compra ="+ fk_compra + "ORDER BY pk_item");
        
        ArrayList<CompraItem> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_item");
            int fkCompra = rs.getInt("fk_compra");
            int fkProduto = rs.getInt("fk_produto");
            int qtd = rs.getInt("qtd");
            float valorUnitario = rs.getFloat("valor_unitario");
            
            Produto produto = ProdutoDAO.retrieve(fkProduto);
 
            aux.add(new CompraItem(pk, fkCompra, produto, qtd, valorUnitario));
        }
            
        return aux;
    }
    
    public static void update(CompraItem compraItem) throws SQLException{
        
        if(compraItem.getStatus() != CompraItem.ALTERADO){
            return;
        }
       
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE compra_item SET fk_compra = ?, fk_produto = ?, qtd = ?, valor_unitario = ? WHERE pk_item = ?"
        );
         
        stm.setInt(1, compraItem.getFkCompra());
        stm.setInt(2, compraItem.getProduto().getPk());
        stm.setInt(3, compraItem.getQtd());
        stm.setFloat(4, compraItem.getValorUnitario());

        stm.execute();
        
        stm.close();
        
        compraItem.resetStatus();
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM compra_item WHERE pk_item="+ pk);
    }
    
    public static void delete(CompraItem compraItem) throws SQLException{
        delete(compraItem.getPk());
    }
}
