/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Grade;
import entity.Subject;
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
public class GradeDBContext extends DBContext<Grade> {

    @Override
    public ArrayList<Grade> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Grade> getGradeBySubject(int subid) {
        ArrayList<Grade> grades = new ArrayList<>();

        try {
            String sql = "SELECT g.item, g.weight, g.value\n"
                    + "FROM     Grade g INNER JOIN\n"
                    + "                  Subjects s ON g.subid = s.subid\n"
                    + "				  where s.subid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, subid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Grade g = new Grade();
                g.setItem(rs.getString("item"));
                g.setWeight((int)(rs.getFloat("weight")*100));
                g.setValue(rs.getFloat("value"));
                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grades;
    }

}
