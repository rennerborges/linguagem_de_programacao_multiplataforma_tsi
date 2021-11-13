/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import t1.pkg1.controller.Cliente;

/**
 *
 * @author renner
 */
public class ClienteDAO {
    public static void create(Cliente cliente) throws SQLException{
        Connection conn = BancoDados.createConnection();
        
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO cliente (nome, cpf) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCpf());
        
        stm.execute();
        
        ResultSet rs = stm.getGeneratedKeys();
        
        rs.next();
        
        int pk_cliente = rs.getInt(1);
        cliente.setPk_cliente(pk_cliente);

        stm.close();
    }
}
