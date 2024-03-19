/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Semester;
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
public class SubjectDBContext extends DBContext<Subject> {

    @Override
    public ArrayList<Subject> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Subject> getSubjectBySemester(int semid) {
        ArrayList<Subject> subjects = new ArrayList<>();

        try {
            String sql = "select s.subid, s.subname from\n"
                    + "		Subjects s join Semesters se on se.semid = s.semid\n"
                    + "		where se.semid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, semid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubid(rs.getInt("subid"));
                s.setSubname(rs.getString("subname"));
                subjects.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public ArrayList<Subject> getSubjectByStudent(int sid, int semid) {
        ArrayList<Subject> subjects = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT Subjects.subcode, Subjects.subid\n"
                    + "FROM     [Group] INNER JOIN\n"
                    + "                  GroupStudents ON [Group].gid = GroupStudents.gid INNER JOIN\n"
                    + "                  Students ON [Group].sid = Students.sid INNER JOIN\n"
                    + "                  Subjects ON GroupStudents.subid = Subjects.subid\n"
                    + "				  inner join Semesters on Subjects.semid = Semesters.semid\n"
                    + "				  where Students.sid = ? and Semesters.semid = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            st.setInt(2, semid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubid(rs.getInt("subid"));
                s.setSubcode(rs.getString("subcode"));
                subjects.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

}
