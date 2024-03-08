/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendance;
import entity.GroupStudent;
import entity.Lecturer;
import entity.Room;
import entity.Session;
import entity.Subject;
import entity.TimeSlot;
import java.sql.Date;
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
public class ScheduleDBContext extends DBContext<Attendance> {

    public ArrayList<Session> getBy(int sid, Date from, Date to) {
        ArrayList<Session> schedules = new ArrayList<>();
        try {
            String sql = "select t.tid, t.tname,t.start, t.[end], r.rid, r.rname, sub.subcode,ses.seid, ses.date, ses.isAttend,stu.first_name, stu.last_name\n"
                    + "from\n"
                    + "		Subjects sub inner join GroupStudents g on sub.subid = g.subid\n"
                    + "		inner join Sessions ses on ses.gid = g.gid\n"
                    + "		inner join TimeSlot t on ses.tid = t.tid\n"
                    + "		inner join Room r on r.rid = ses.rid\n"
                    + "		inner join Attendance a on ses.seid = a.seid \n"
                    + "		inner join Students stu on a.sid = stu.sid\n"
                    + "		where stu.sid = ? and ses.date >= ? and ses.date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session ses = new Session();
                GroupStudent g = new GroupStudent();
                TimeSlot slot = new TimeSlot();
                Room r = new Room();
                Subject su = new Subject();
                Attendance a = new Attendance();
                
                ses.setSeid(rs.getInt("seid"));
                ses.setIsAttend(rs.getBoolean("isAttend"));
                ses.setDate(rs.getDate("date"));

                
                su.setSubcode(rs.getString("subcode"));
                g.setSubject(su);

                ses.setGroupStudent(g);

                slot.setTid(rs.getInt("tid"));
                slot.setTname(rs.getString("tname"));
                slot.setStart(rs.getString("start"));
                slot.setEnd(rs.getString("end"));
                ses.setSlot(slot);

                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                ses.setRoom(r);

                schedules.add(ses);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return schedules;
    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
