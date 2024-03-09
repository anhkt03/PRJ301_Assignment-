/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Department;
import entity.Semester;
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
public class SemesterDBContext extends DBContext<Semester>{

    @Override
    public ArrayList<Semester> list() {
        ArrayList<Semester> semesters = new ArrayList<>();
        try {
            String sql = "SELECT semid, semName FROM Semesters";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Semester s = new Semester();
                s.setSemid(rs.getInt("semid"));
                s.setSemName(rs.getString("semName"));
                semesters.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semesters;
    }
    
}
