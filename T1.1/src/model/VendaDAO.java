/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import t1.pkg1.controller.Venda;
import t1.pkg1.controller.Item;
import t1.pkg1.controller.Funcionario;
import t1.pkg1.controller.Cliente;

/**
 *
 * @author renner
 */
public class VendaDAO {
    public static void create(Venda venda) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        if(venda.getVendedor().getPk() == 0){
            FuncionarioDAO.create(venda.getVendedor());
        }else{
            FuncionarioDAO.update(venda.getVendedor());
        }
        
        if(venda.getCliente().getPk_cliente() == 0){
            ClienteDAO.create(venda.getCliente());
        }else{
            ClienteDAO.update(venda.getCliente());
        }
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO venda (fk_cliente, fk_vendedor, numero, data) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        java.sql.Date date = new java.sql.Date (venda.getDate().getTime());
        
        stm.setInt(1, venda.getCliente().getPk_cliente());
        stm.setInt(2, venda.getVendedor().getPk());
        stm.setInt(3, venda.getNumero());
        stm.setDate(4, date);
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk = rs.getInt(1);
        venda.setPk(pk);
        
        ArrayList<Item> itens = venda.getVendasItens();
         
        if(itens != null) {
            for(Item item : itens){
                item.setFk(pk);
     
                VendaItemDAO.create(item);
            }
        }

        stm.close();
    }
    
    public static Venda retrieve(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda WHERE pk_venda="+ pk);
        
        Venda venda = null;
        
        if(rs.next()){
            int fkCliente = rs.getInt("fk_cliente");
            int fkVendedor = rs.getInt("fk_vendedor");
            int numero = rs.getInt("numero");
            Date data = rs.getDate("data");
            
            Funcionario funcionario = FuncionarioDAO.retrieve(fkVendedor);
            Cliente cliente = ClienteDAO.retrieve(fkCliente);
            ArrayList<Item> vendasItens = VendaItemDAO.retrieveAllByVenda(pk);
            
            venda = new Venda(pk, cliente, funcionario, numero, data, vendasItens);
        }
        
        return venda;
    }
    
    public static Venda retrieve(Venda venda) throws SQLException{
        return retrieve(venda.getPk());
    }
    
    public static ArrayList<Venda> retrieveAll() throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM venda");
        
        ArrayList<Venda> aux = new ArrayList<>();
        
        while(rs.next()){
            int pk = rs.getInt("pk_venda");
            int fkCliente = rs.getInt("fk_cliente");
            int fkVendedor = rs.getInt("fk_vendedor");
            int numero = rs.getInt("numero");
            Date data = rs.getDate("data");
            
            Funcionario funcionario = FuncionarioDAO.retrieve(fkVendedor);
            Cliente cliente = ClienteDAO.retrieve(fkCliente);
            ArrayList<Item> vendasItens = VendaItemDAO.retrieveAllByVenda(pk);
            
            aux.add(new Venda(pk, cliente, funcionario, numero, data, vendasItens));
        }
        
        return aux;
    }
    
    public static void delete(int pk) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        VendaItemDAO.deleteAllByVenda(pk);
        
        conn.createStatement().execute("DELETE FROM venda WHERE pk_venda ="+ pk);

    }
    
    public static void delete(Venda venda) throws SQLException{
        delete(venda.getPk());
    }
    
    public static void update(Venda venda) throws SQLException{
         Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE venda SET fk_cliente = ?, fk_vendedor = ?, numero = ?, data = ? WHERE pk_venda= ?"
        );
        
        java.sql.Date date = new java.sql.Date (venda.getDate().getTime());
        
        stm.setInt(1, venda.getCliente().getPk_cliente());
        stm.setInt(2, venda.getVendedor().getPk());
        stm.setInt(3, venda.getNumero());
        stm.setDate(4, date);
        stm.setInt(5, venda.getPk());


        stm.execute();
        
        stm.close();
        
        ArrayList<Item> itens = venda.getVendasItens();
        
        //Percorrendo o array de endereços verificando se existe para atualizar
        //Ou se é para ser criado        
        
        for(int i = 0; i < itens.size(); i++ ){
            Item item = itens.get(i);
            
            if(item.getStatus() == Item.EXCLUIDO){
                VendaItemDAO.delete(item);
                itens.remove(item);
                i--;
                continue;
            }
            
            if(item.getPk()== 0){
                item.setFk(venda.getPk());
                VendaItemDAO.create(item);
                continue;
            }
            
            VendaItemDAO.update(item);
        }
      
        FuncionarioDAO.update(venda.getVendedor());
        ClienteDAO.update(venda.getCliente());
    }
}
