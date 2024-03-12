/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Lecturer;

import controller.authorization.Authorization;
import dal.AttendanceDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import entity.Account;
import entity.Attendance;
import entity.Role;
import entity.Session;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.apache.catalina.ha.ClusterSession;

/**
 *
 * @author kieuthanhtheanh
 */
@WebServlet(name = "TakeAttendance", urlPatterns = {"/attend"})
public class TakeAttendance extends Authorization {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int seid = Integer.parseInt(request.getParameter("seid"));
//        SessionDBContext sesDB = new SessionDBContext();
//        ArrayList<Attendance> a = sesDB.getAttendBySession(seid);
//
//        request.setAttribute("attends", a);
//        request.getRequestDispatcher("view/lecturer/attend.jsp").forward(request, response);
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//   @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int seid = Integer.parseInt(request.getParameter("seid"));
//        StudentDBContext stDB = new StudentDBContext();
//        ArrayList<Student> students = stDB.getBySession(seid);
//        ArrayList<Attendance> attends = new ArrayList<>();
//        Session ses = new Session();
//        ses.setSeid(seid);
//        for (Student student : students) {
//            Attendance attend = new Attendance();
//            attend.setSession(ses);
//            attend.setStudent(student);
//            attend.setIsAttend(request.getParameter("present" + student.getSid()).equals("yes"));
//            attend.setComment(request.getParameter("comment" + student.getSid()));
//            attends.add(attend);
//        }
//
//        AttendanceDBContext aDB = new AttendanceDBContext();
//        aDB.batchupdateBySession(seid, attends);
//        response.sendRedirect("attend?seid=" + seid);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        int seid = Integer.parseInt(request.getParameter("seid"));
        StudentDBContext stDB = new StudentDBContext();
        ArrayList<Student> students = stDB.getBySession(seid);
        ArrayList<Attendance> attends = new ArrayList<>();
        Session ses = new Session();
        ses.setSeid(seid);
        for (Student student : students) {
            Attendance attend = new Attendance();
            attend.setSession(ses);
            attend.setStudent(student);
            attend.setIsAttend(request.getParameter("present" + student.getSid()).equals("yes"));
            attend.setComment(request.getParameter("comment" + student.getSid()));
            attends.add(attend);
        }

        AttendanceDBContext aDB = new AttendanceDBContext();
        aDB.batchupdateBySession(seid, attends);
        response.sendRedirect("attend?seid=" + seid);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        int seid = Integer.parseInt(request.getParameter("seid"));
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Attendance> a = sesDB.getAttendBySession(seid);

        request.setAttribute("attends", a);
        request.getRequestDispatcher("view/lecturer/attend.jsp").forward(request, response);
    }

}
