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

    public ArrayList<Attendance> getBy(int sid, Date from, Date to) {
        ArrayList<Attendance> attends = new ArrayList<>();
        try {
            String sql = "select t.tid, t.tname,t.start, t.[end], r.rid, r.rname, sub.subcode,ses.seid, ses.date, a.aid,\n"
                    + "a.seid, a.isAttend, a.recordtime, a.comment\n"
                    + "from\n"
                    + "                  	Subjects sub inner join GroupStudents gs on sub.subid = gs.subid\n"
                    + "                  	inner join Sessions ses on ses.gid = gs.gid\n"
                    + "                  	inner join TimeSlot t on ses.tid = t.tid\n"
                    + "                 	inner join Room r on r.rid = ses.rid\n"
                    + "					JOIN [Group]  g ON gs.gid = g.gid\n"
                    + "                   	inner join Students stu on g.sid = stu.sid\n"
                    + "					LEFt join Attendance a on  a.sid = stu.sid AND ses.seid = a.seid\n"
                    + "                	where stu.sid = ? and ses.[date] >= ? and ses.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Session session = new Session();
                TimeSlot t = new TimeSlot();
                Room r = new Room();
                Attendance a = new Attendance();
                Subject sub = new Subject();
                GroupStudent g = new GroupStudent();
                
                t.setTid(rs.getInt("tid"));
                t.setTname(rs.getString("tname"));
                t.setStart(rs.getString("start"));
                t.setEnd(rs.getString("end"));
                session.setSlot(t);
                
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                session.setRoom(r);
                
                sub.setSubcode(rs.getString("subcode"));
                g.setSubject(sub);
                session.setGroupStudent(g);
                
                session.setSeid(rs.getInt("seid"));
                session.setDate(rs.getDate("date"));
                a.setIsAttend(rs.getInt("isAttend"));
                a.setAid(rs.getInt("aid"));
                a.setRecordtime(rs.getTimestamp("recordtime"));
                a.setComment(rs.getString("comment"));
                a.setSession(session);
                
                attends.add(a);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return attends;
    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
