/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Compra;
import t1.pkg1.controller.CompraItem;
import t1.pkg1.controller.Fornecedor;

/**
 *
 * @author renner
 */
public class CompraDAO {
    public static void create(Compra compra) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
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
     
        if(compra.getFornecedor().getPk() == 0){
            FornecedorDAO.create(compra.getFornecedor());
        }else{
            FornecedorDAO.update(compra.getFornecedor());
        }
        
        ArrayList<CompraItem> compras = compra.getComprasItens();
         
        if(compras != null) {
            for(CompraItem compraItem : compras){
                compraItem.setFkCompra(pk);
     
                CompraItemDAO.create(compraItem);
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
            ArrayList<CompraItem> comprasItens = CompraItemDAO.retrieveAllByCompra(pk);
            
            compra = new Compra(pk, fornecedor, numero, data, comprasItens);
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
            ArrayList<CompraItem> comprasItens = CompraItemDAO.retrieveAllByCompra(pk);
            
            aux.add(new Compra(pk, fornecedor, numero, data, comprasItens));
        }
        
        return aux;
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        CompraItemDAO.deleteAllByCompra(pk);
        
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
        
        ArrayList<CompraItem> comprasItens = compra.getComprasItens();
        
        //Percorrendo o array de endereços verificando se existe para atualizar
        //Ou se é para ser criado        
        
        for(int i = 0; i < comprasItens.size(); i++ ){
            CompraItem compraItem = comprasItens.get(i);
            
            if(compraItem.getStatus() == CompraItem.EXCLUIDO){
                CompraItemDAO.delete(compraItem);
                comprasItens.remove(compraItem);
                i--;
                continue;
            }
            
            if(compraItem.getPk()== 0){
                compraItem.setFkCompra(compra.getPk());
                CompraItemDAO.create(compraItem);
                continue;
            }
//            
            CompraItemDAO.update(compraItem);
        }
//        
        FornecedorDAO.update(compra.getFornecedor());
    }
}
