/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Financeiro;

/**
 *
 * @author renner
 */
public class FinanceiroSaidaDAO {
    public static void create(Financeiro financeiro) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO financeiro_saida (fk_compra, data_emissao, data_vencimento, data_baixa, valor, forma_pagamento) VALUES (?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        java.sql.Date dataEmissao = new java.sql.Date (financeiro.getDataEmissao().getTime());
        java.sql.Date dataVencimento = new java.sql.Date (financeiro.getDataVencimento().getTime());
        java.sql.Date dataBaixa = financeiro.getDataBaixa() == null ? null : new java.sql.Date (financeiro.getDataBaixa().getTime());

        stm.setInt(1, financeiro.getFk());
        stm.setDate(2, dataEmissao);
        stm.setDate(3, dataVencimento);
        stm.setDate(4, dataBaixa);
        stm.setFloat(5, financeiro.getValor());
        stm.setString(6, financeiro.getFormaPagamento());

        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk = rs.getInt(1);
        financeiro.setPk(pk);
        
        stm.close();
    }
    
    public static Financeiro retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_saida WHERE pk_financeiro=" + pk);
        
        Financeiro financeiro = null;
        
        if(rs.next()){
            int fkCompra = rs.getInt("fk_compra");
            Date dataEmissao = rs.getDate("data_emissao");
            Date dataVencimento = rs.getDate("data_vencimento");
            Date dataBaixa = rs.getDate("data_baixa");
            float valor = rs.getFloat("valor");
            String formaPagamento = rs.getString("forma_pagamento");
            
            
            financeiro = new Financeiro(pk, fkCompra, dataEmissao, dataVencimento, dataBaixa, valor, formaPagamento);
        }
            
        return financeiro;
    }
    
    public static Financeiro retrieve(Financeiro financeiro) throws SQLException{
        return retrieve(financeiro.getPk());
    }
    
    public static ArrayList<Financeiro> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_saida ORDER BY pk_financeiro");
        
        ArrayList<Financeiro> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_financeiro");
            int fkCompra = rs.getInt("fk_compra");
            Date dataEmissao = rs.getDate("data_emissao");
            Date dataVencimento = rs.getDate("data_vencimento");
            Date dataBaixa = rs.getDate("data_baixa");
            float valor = rs.getFloat("valor");
            String formaPagamento = rs.getString("forma_pagamento");
            
            aux.add(new Financeiro(pk, fkCompra, dataEmissao, dataVencimento, dataBaixa, valor, formaPagamento));
        }
            
        return aux;
    }
    
    public static ArrayList<Financeiro> retrieveAllByCompra(int fkCompra) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_saida WHERE fk_compra="+ fkCompra + "ORDER BY pk_financeiro");
        
        ArrayList<Financeiro> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_financeiro");
            Date dataEmissao = rs.getDate("data_emissao");
            Date dataVencimento = rs.getDate("data_vencimento");
            Date dataBaixa = rs.getDate("data_baixa");
            float valor = rs.getFloat("valor");
            String formaPagamento = rs.getString("forma_pagamento");
            
            aux.add(new Financeiro(pk, fkCompra, dataEmissao, dataVencimento, dataBaixa, valor, formaPagamento));
        }
            
        return aux;
    }
    
    public static void update(Financeiro financeiro) throws SQLException{
        
        if(financeiro.getStatus() != Financeiro.ALTERADO){
            return;
        }
       
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE financeiro_saida SET fk_compra = ?, data_emissao = ?, data_vencimento = ?, data_baixa = ?, valor = ?, forma_pagamento = ?  WHERE pk_financeiro = ?"
        );
        
        java.sql.Date dataEmissao = new java.sql.Date (financeiro.getDataEmissao().getTime());
        java.sql.Date dataVencimento = new java.sql.Date (financeiro.getDataVencimento().getTime());
        java.sql.Date dataBaixa = financeiro.getDataBaixa() == null ? null : new java.sql.Date (financeiro.getDataBaixa().getTime());

        stm.setInt(1, financeiro.getFk());
        stm.setDate(2, dataEmissao);
        stm.setDate(3, dataVencimento);
        stm.setDate(4, dataBaixa);
        stm.setFloat(5, financeiro.getValor());
        stm.setString(6, financeiro.getFormaPagamento());
        stm.setInt(7, financeiro.getPk());

        stm.execute();
        
        stm.close();
        
        financeiro.resetStatus();
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM financeiro_saida WHERE pk_financeiro ="+ pk);
    }
    
    public static void delete(Financeiro financeiro) throws SQLException{
        delete(financeiro.getPk());
    }
    
    public static void deleteAllByCompra(int fk_compra) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM financeiro_saida WHERE fk_compra="+ fk_compra);
    }
}
