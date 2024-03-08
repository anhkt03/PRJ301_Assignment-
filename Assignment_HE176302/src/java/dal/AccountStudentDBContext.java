/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.AccountStudent;
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
public class AccountStudentDBContext extends DBContext<AccountStudent>{

    
    public AccountStudent getByUsernamePasswordOfStudent(String username, String password) {
        try {
            String sql = "SELECT username,password, accountid FROM AccountStudent\n"
                    + " WHERE username = ? AND password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2,password);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                AccountStudent acc = new AccountStudent();
                acc.setUsername(username);
                acc.setPassword(password);
                acc.setAccountid(rs.getInt("accountid"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public ArrayList<AccountStudent> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
