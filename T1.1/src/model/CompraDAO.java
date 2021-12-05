/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import t1.pkg1.controller.Compra;
import t1.pkg1.controller.Item;
import t1.pkg1.controller.Fornecedor;
import t1.pkg1.controller.Financeiro;


/**
 *
 * @author renner
 */
public class CompraDAO {
    public static void create(Compra compra) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        if(compra.getFornecedor().getPk() == 0){
            FornecedorDAO.create(compra.getFornecedor());
        }
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO compra (fk_fornecedor, numero, data) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        java.sql.Date date = new java.sql.Date (compra.getData().getTime());
        
        stm.setInt(1, compra.getFornecedor().getPk());
        stm.setInt(2, compra.getNumero());
        stm.setDate(3, date);
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk = rs.getInt(1);
        compra.setPk(pk);
        
        ArrayList<Item> compras = compra.getComprasItens();
         
        if(compras != null) {
            for(Item compraItem : compras){
                compraItem.setFk(pk);
     
                CompraItemDAO.create(compraItem);
            }
        }
        
        ArrayList<Financeiro> pagamentos = compra.getPagamentos();
         
        if(pagamentos != null) {
            for(Financeiro financeiro : pagamentos){
                financeiro.setFk(pk);
     
                FinanceiroSaidaDAO.create(financeiro);
            }
        }

        stm.close();
    }
    
    public static Compra retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM compra WHERE pk_compra ="+ pk);
        
        Compra compra = null;
        
        if(rs.next()){
            int fkFornecedor = rs.getInt("fk_fornecedor");
            int numero = rs.getInt("numero");
            Date data = rs.getDate("data");
            
            Fornecedor fornecedor = FornecedorDAO.retrieve(fkFornecedor);
            ArrayList<Item> comprasItens = CompraItemDAO.retrieveAllByCompra(pk);
            ArrayList<Financeiro> pagamentos = FinanceiroSaidaDAO.retrieveAllByCompra(pk);
            
            compra = new Compra(pk, fornecedor, numero, data, comprasItens, pagamentos);
        }
        
        return compra;
    }
    
    public static Compra retrieve(Compra compra) throws SQLException{
        return retrieve(compra.getPk());
    }
    
    public static ArrayList<Compra> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM compra");
        
        ArrayList<Compra> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_compra");
            int fkFornecedor = rs.getInt("fk_fornecedor");
            int numero = rs.getInt("numero");
            Date data = rs.getDate("data");
            
            Fornecedor fornecedor = FornecedorDAO.retrieve(fkFornecedor);
            ArrayList<Item> comprasItens = CompraItemDAO.retrieveAllByCompra(pk);
            ArrayList<Financeiro> pagamentos = FinanceiroSaidaDAO.retrieveAllByCompra(pk);
            
            aux.add(new Compra(pk, fornecedor, numero, data, comprasItens, pagamentos));
        }
        
        return aux;
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        CompraItemDAO.deleteAllByCompra(pk);
        FinanceiroSaidaDAO.deleteAllByCompra(pk);
        
        conn.createStatement().execute("DELETE FROM compra WHERE pk_compra ="+ pk);

    }
    
    public static void delete(Compra compra) throws SQLException{
        delete(compra.getPk());
    }
    
    public static void update(Compra compra) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE compra SET fk_fornecedor = ?, numero = ?, data = ? WHERE pk_compra= ?"
        );
        
        java.sql.Date date = new java.sql.Date (compra.getData().getTime());
        
        stm.setInt(1, compra.getFornecedor().getPk());
        stm.setInt(2, compra.getNumero());
        stm.setDate(3, date);
        stm.setInt(4, compra.getPk());


        stm.execute();
        
        stm.close();
        
        ArrayList<Item> comprasItens = compra.getComprasItens();
        
        //Percorrendo o array de endereços verificando se existe para atualizar
        //Ou se é para ser criado        
        
        for(int i = 0; i < comprasItens.size(); i++ ){
            Item compraItem = comprasItens.get(i);
            
            if(compraItem.getStatus() == Item.EXCLUIDO){
                CompraItemDAO.delete(compraItem);
                comprasItens.remove(compraItem);
                i--;
                continue;
            }
            
            if(compraItem.getPk()== 0){
                compraItem.setFk(compra.getPk());
                CompraItemDAO.create(compraItem);
                continue;
            }
            
            CompraItemDAO.update(compraItem);
        }
        
        ArrayList<Financeiro> pagamentos = compra.getPagamentos();
        
        //Percorrendo o array de endereços verificando se existe para atualizar
        //Ou se é para ser criado        
        
        for(int i = 0; i < pagamentos.size(); i++ ){
            Financeiro financeiro = pagamentos.get(i);
            
            if(financeiro.getStatus() == Financeiro.EXCLUIDO){
                FinanceiroSaidaDAO.delete(financeiro);
                pagamentos.remove(financeiro);
                i--;
                continue;
            }
            
            if(financeiro.getPk()== 0){
                financeiro.setFk(compra.getPk());
                FinanceiroSaidaDAO.create(financeiro);
                continue;
            }
            
            FinanceiroSaidaDAO.update(financeiro);
        }
      
        FornecedorDAO.update(compra.getFornecedor());
    }
}
