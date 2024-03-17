/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Lecturer;

import controller.authorization.Authorization;
import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import entity.Account;
import entity.Role;
import entity.Session;
import entity.TimeSlot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.sql.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.temporal.WeekFields;
import java.util.ArrayList;

/**
 *
 * @author kieuthanhtheanh
 */
@WebServlet(name = "TimeTableController", urlPatterns = {"/timetable"})
public class TimeTableController extends Authorization {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TimeTableController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimeTableController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
//        
//        int leid = (int) request.getSession().getAttribute("accountid");
//        
//        
//        String raw_year = request.getParameter("year");
//        String raw_week = request.getParameter("week");
//        java.sql.Date from =null;
//        java.sql.Date to = null;
//        String[] week;
//        String weekfrom = null;
//        String weekto = null;
//        if (raw_week != null) {
//            week = DateTimeHelper.splitString(raw_week);
//            weekfrom = week[0];
//            weekto = week[1];
//            
//        }
//
//        String beforeFrom = raw_year + "/" + weekfrom;
//        String  beforeTo= raw_year + "/" + weekto;
//        String afterfrom = DateTimeHelper.changeDateFormat(beforeFrom);
//        String afterto = DateTimeHelper.changeDateFormat(beforeTo);
//        from = DateTimeHelper.convertStringToSqlDate(afterfrom);
//        to = DateTimeHelper.convertStringToSqlDate(afterto);
//        
//        ArrayList<java.sql.Date> dates= null;
//        if(from!= null && to != null) {
//            dates = DateTimeHelper.getDatesBetween(from, to);
//        }
//        
//        TimeSlotDBContext slotDB = new TimeSlotDBContext();
//        ArrayList<TimeSlot> slots = slotDB.list();
//        
//        SessionDBContext sessionDB = new SessionDBContext();
//        ArrayList<Session> sessions = sessionDB.getBy(leid, from, to);
//       
//
//        request.setAttribute("slots", slots);
//        request.setAttribute("sessions", sessions);
//        request.setAttribute("dates", dates);
//        request.getRequestDispatcher("view/lecturer/timetable.jsp").forward(request, response);
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        int leid = (int) request.getSession().getAttribute("accountid");
        
        
        String raw_year = request.getParameter("year");
        String raw_week = request.getParameter("week");
        request.setAttribute("raw_week", raw_week);
        java.sql.Date from =null;
        java.sql.Date to = null;
        String[] week;
        String weekfrom = null;
        String weekto = null;
        if (raw_week != null) {
            week = DateTimeHelper.splitString(raw_week);
            weekfrom = week[0];
            weekto = week[1];
            
        }

        String beforeFrom = raw_year + "/" + weekfrom;
        String  beforeTo= raw_year + "/" + weekto;
        String afterfrom = DateTimeHelper.changeDateFormat(beforeFrom);
        String afterto = DateTimeHelper.changeDateFormat(beforeTo);
        from = DateTimeHelper.convertStringToSqlDate(afterfrom);
        to = DateTimeHelper.convertStringToSqlDate(afterto);
        
        ArrayList<java.sql.Date> dates= null;
        if(from!= null && to != null) {
            dates = DateTimeHelper.getDatesBetween(from, to);
        }
        
        TimeSlotDBContext slotDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = slotDB.list();
        
        SessionDBContext sessionDB = new SessionDBContext();
        ArrayList<Session> sessions = sessionDB.getBy(leid, from, to);
       
        request.setAttribute("year", raw_year);
        request.setAttribute("week", raw_week);
        request.setAttribute("slots", slots);
        request.setAttribute("sessions", sessions);
        request.setAttribute("dates", dates);
        request.getRequestDispatcher("view/lecturer/timetable.jsp").forward(request, response);

    }

}
