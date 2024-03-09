/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieuthanhtheanh
 */
public class AccountDBContext extends DBContext<Account>{
    
    public Account getByUsernamePassword(String username, String password) {
        try {
            String sql = "SELECT username,password, type, idtype FROM Account\n"
                    + " WHERE username = ? AND password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2,password);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                Account a = new Account();
                a.setUsername(username);
                a.setPassword(password);
                a.setType(rs.getString("type"));
                a.setIdtype(rs.getInt("idtype"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
