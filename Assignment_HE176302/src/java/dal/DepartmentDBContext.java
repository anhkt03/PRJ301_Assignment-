/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Department;
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
public class DepartmentDBContext extends DBContext<Department>{

    @Override
    public ArrayList<Department> list() {
        ArrayList<Department> departments = new ArrayList<>();
        try {
            String sql = "SELECT depid, depname FROM Departments";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Department d = new Department();
                d.setDepid(rs.getInt("depid"));
                d.setDepname(rs.getString("depname"));
                departments.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departments;
    }
    
}
