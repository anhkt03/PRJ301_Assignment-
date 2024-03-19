/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Subject;
import entity.SubjectDetail;
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
public class SubjectDetailDBContext extends DBContext<SubjectDetail> {

    
    public ArrayList<SubjectDetail> list(String subcode) {
        ArrayList<SubjectDetail> detail = new ArrayList<>();
        try {
            String sql = "SELECT sd.category, sd.type, sd.weight, sd.duration, sd.typeQuestion, sd.noQuestion\n"
                    + "FROM     SubjectDetails sd INNER JOIN\n"
                    + "                  Subjects s ON sd.subid = s.subid\n"
                    + "				  where s.subcode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, subcode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SubjectDetail d = new SubjectDetail();
                d.setCategory(rs.getString("category"));
                d.setType(rs.getString("type"));
                d.setWeight((int)(rs.getFloat("weight")*100));
                d.setDuration(rs.getString("duration"));
                d.setTypeQuestion(rs.getString("typeQuestion"));
                d.setNoQuestion(rs.getInt("noQuestion"));
                detail.add(d);
            }
        }catch (SQLException ex) {
            Logger.getLogger(SubjectDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return detail;
    }

    @Override
    public ArrayList<SubjectDetail> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
