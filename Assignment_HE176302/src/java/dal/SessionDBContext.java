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
import entity.Student;
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
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> getBy(int leid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "select ses.seid, ses.date, g.gid, g.gname, su.subid, su.subname,su.subcode,\n"
                    + "		t.tid, t.tname, t.start, t.[end], r.rid, r.rname, le.leid, le.last_name, ses.isAttend\n"
                    + "from Sessions ses inner join GroupStudents g on g.gid = ses.gid\n"
                    + "				  inner join Subjects su on su.subid = g.subid\n"
                    + "				  inner join TimeSlot t on t.tid = ses.tid\n"
                    + "				  inner join Room r on r.rid = ses.rid\n"
                    + "				  inner join Lecturers le on le.leid = ses.leid\n"
                    + "				  where ses.leid = ? and ses.[date] >= ? and ses.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session ses = new Session();
                GroupStudent g = new GroupStudent();
                Subject su = new Subject();
                TimeSlot slot = new TimeSlot();
                Room r = new Room();
                Lecturer lec = new Lecturer();

                ses.setSeid(rs.getInt("seid"));
                ses.setIsAttend(rs.getBoolean("isAttend"));
                ses.setDate(rs.getDate("date"));

                g.setGid(rs.getInt("gid"));
                g.setGname(rs.getString("gname"));
                su.setSubid(rs.getInt("subid"));
                su.setSubname(rs.getString("subname"));
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

                lec.setLeid(rs.getInt("leid"));
                lec.setLename(rs.getString("last_name"));
                ses.setLecturer(lec);

                sessions.add(ses);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessions;
    }

    public ArrayList<Attendance> getAttendBySession(int seid) {
        ArrayList<Attendance> attends = new ArrayList<>();
        try {
            String sql = "SELECT Sessions.seid, Students.sid, Students.MSSV, (Students.first_name+''+Students.mid_name+''+Students.last_name) as [name]\n"
                    + ",Attendance.aid, Attendance.comment, Attendance.isAttend, Attendance.recordtime\n"
                    + "FROM     [Group] INNER JOIN\n"
                    + "                  GroupStudents ON [Group].gid = GroupStudents.gid INNER JOIN\n"
                    + "                  Sessions ON GroupStudents.gid = Sessions.gid INNER JOIN\n"
                    + "                  Students ON [Group].sid = Students.sid\n"
                    + "				  left join Attendance on Attendance.seid = Sessions.seid and Attendance.sid = Students.sid\n"
                    + "				  where Sessions.seid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, seid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Session ses = new Session();
                ses.setSeid(rs.getInt("seid"));
                a.setSession(ses);

                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setMSSV(rs.getString("MSSV"));
                s.setSname(rs.getString("name"));
                a.setStudent(s);
                a.setAid(rs.getInt("aid"));
                if (a.getAid() != 0) {
                    a.setComment(rs.getString("comment"));
                    a.setIsAttend(rs.getBoolean("isAttend"));
                    a.setRecordtime(rs.getTimestamp("recordtime"));
                }

                attends.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attends;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
