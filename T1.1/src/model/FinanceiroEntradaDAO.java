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
public class FinanceiroEntradaDAO {
     public static void create(Financeiro financeiro) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO financeiro_entrada (fk_venda, data_emissao, data_vencimento, data_baixa, valor, forma_recebimento) VALUES (?,?,?,?,?,?)",
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
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_entrada WHERE pk_financeiro=" + pk);
        
        Financeiro financeiro = null;
        
        if(rs.next()){
            int fkVenda = rs.getInt("fk_venda");
            Date dataEmissao = rs.getDate("data_emissao");
            Date dataVencimento = rs.getDate("data_vencimento");
            Date dataBaixa = rs.getDate("data_baixa");
            float valor = rs.getFloat("valor");
            String formaRecebimento = rs.getString("forma_recebimento");
            
            
            financeiro = new Financeiro(pk, fkVenda, dataEmissao, dataVencimento, dataBaixa, valor, formaRecebimento);
        }
            
        return financeiro;
    }
    
    public static Financeiro retrieve(Financeiro financeiro) throws SQLException{
        return retrieve(financeiro.getPk());
    }
    
    public static ArrayList<Financeiro> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_entrada ORDER BY pk_financeiro");
        
        ArrayList<Financeiro> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_financeiro");
            int fkVenda = rs.getInt("fk_venda");
            Date dataEmissao = rs.getDate("data_emissao");
            Date dataVencimento = rs.getDate("data_vencimento");
            Date dataBaixa = rs.getDate("data_baixa");
            float valor = rs.getFloat("valor");
            String formaRecebimento = rs.getString("forma_recebimento");
            
            aux.add(new Financeiro(pk, fkVenda, dataEmissao, dataVencimento, dataBaixa, valor, formaRecebimento));
        }
            
        return aux;
    }
    
    public static ArrayList<Financeiro> retrieveAllByVenda(int fk_venda) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM financeiro_entrada WHERE fk_venda="+ fk_venda + "ORDER BY pk_financeiro");
        
        ArrayList<Financeiro> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_financeiro");
            int fkVenda = rs.getInt("fk_venda");
            Date dataEmissao = rs.getDate("data_emissao");
            Date dataVencimento = rs.getDate("data_vencimento");
            Date dataBaixa = rs.getDate("data_baixa");
            float valor = rs.getFloat("valor");
            String formaRecebimento = rs.getString("forma_recebimento");
            
            aux.add(new Financeiro(pk, fkVenda, dataEmissao, dataVencimento, dataBaixa, valor, formaRecebimento));
        }
            
        return aux;
    }
    
    public static void update(Financeiro financeiro) throws SQLException{
        
        if(financeiro.getStatus() != Financeiro.ALTERADO){
            return;
        }
       
        Connection conn = BancoDados.createConnection();

        PreparedStatement stm = conn.prepareStatement(
                "UPDATE financeiro_entrada SET fk_venda = ?, data_emissao = ?, data_vencimento = ?, data_baixa = ?, valor = ?, forma_recebimento = ?  WHERE pk_financeiro = ?"
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
        
        conn.createStatement().execute("DELETE FROM financeiro_entrada WHERE pk_financeiro ="+ pk);
    }
    
    public static void delete(Financeiro financeiro) throws SQLException{
        delete(financeiro.getPk());
    }
    
    public static void deleteAllByCliente(int fk_venda) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        conn.createStatement().execute("DELETE FROM financeiro_entrada WHERE fk_venda="+ fk_venda);
    }
}
