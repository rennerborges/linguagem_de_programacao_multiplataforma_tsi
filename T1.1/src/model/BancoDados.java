/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import t1.pkg1.Main;

/**
 *
 * @author renner
 */
public class BancoDados {
    
    public static Connection conn;
    
    public static Connection createConnection() {
        
        if(conn == null){
            try {
         
                Class.forName(DBString.DRIVER);

                conn = DriverManager.getConnection(
                    DBString.URL,
                    DBString.USER,
                    DBString.PASSWORD
                ); 

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return conn;
    }
}
