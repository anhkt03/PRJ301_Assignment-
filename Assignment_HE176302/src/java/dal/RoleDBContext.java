/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Role;
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
public class RoleDBContext extends DBContext<Role> {

    public ArrayList<Role> getByUsernameAndUrl(String username, String url) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT distinct Role.roleid, Role.rolename\n"
                    + "FROM     Account INNER JOIN\n"
                    + "                  Role_Account ON Account.username = Role_Account.username INNER JOIN\n"
                    + "                  Role ON Role_Account.roleid = Role.roleid INNER JOIN\n"
                    + "                  Role_Feature ON Role.roleid = Role_Feature.roleid INNER JOIN\n"
                    + "                  Feature ON Role_Feature.fid = Feature.fid\n"
                    + "				  where Account.username = ? and Feature.url=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setRoleid(rs.getInt("roleid"));
                r.setRolename(rs.getString("rolename"));
                roles.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(roles.size());
        return roles;
    }

    @Override
    public ArrayList<Role> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
