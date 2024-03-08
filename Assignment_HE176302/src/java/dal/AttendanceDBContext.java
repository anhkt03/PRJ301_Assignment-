/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendance;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieuthanhtheanh
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public void batchupdateBySession(int seid, ArrayList<Attendance> atts) {
        try {
            connection.setAutoCommit(false);
            String sql_remove_atts = "delete Attendance where seid = ?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setInt(1, seid);
            stm_remove_atts.executeUpdate();

            String sql_insert_att = "INSERT INTO Attendance "
                    + "(seid,"
                    + " sid,"
                    + " isAttend,"
                    + " comment,"
                    + " recordtime) \n"
                    + "VALUES (?, ?, ?, ?, GETDATE());";
            for (Attendance att : atts) {
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setInt(1,seid);
                stm_insert_att.setInt(2, att.getStudent().getSid());
                stm_insert_att.setBoolean(3, att.isIsAttend());
                stm_insert_att.setString(4, att.getComment());
                stm_insert_att.executeUpdate();

            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
