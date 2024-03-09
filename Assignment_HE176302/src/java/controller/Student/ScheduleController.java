/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Student;

import controller.Lecturer.DateTimeHelper;
import dal.SessionDBContext;
import dal.ScheduleDBContext;
import dal.TimeSlotDBContext;
import entity.Session;
import entity.TimeSlot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author kieuthanhtheanh
 */
@WebServlet(name = "ScheduleController", urlPatterns = {"/schedule"})
public class ScheduleController extends HttpServlet {

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
            out.println("<title>Servlet TimeTableContrroller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimeTableContrroller at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sid = (int) request.getSession().getAttribute("accountid");
        //      int sid = (int) request.getSession().getAttribute("accountstudent");

        String raw_year = request.getParameter("year");
        String raw_week = request.getParameter("week");
        java.sql.Date from = null;
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
        String beforeTo = raw_year + "/" + weekto;
        String afterfrom = DateTimeHelper.changeDateFormat(beforeFrom);
        String afterto = DateTimeHelper.changeDateFormat(beforeTo);
        from = DateTimeHelper.convertStringToSqlDate(afterfrom);
        to = DateTimeHelper.convertStringToSqlDate(afterto);

        ArrayList<java.sql.Date> dates = null;
        if (from != null && to != null) {
            dates = DateTimeHelper.getDatesBetween(from, to);
        }

        ScheduleDBContext schedule = new ScheduleDBContext();
        ArrayList<Session> sches = schedule.getBy(sid, from, to);

        TimeSlotDBContext slotDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = slotDB.list();

        request.setAttribute("dates", dates);
        request.setAttribute("slots", slots);
        request.setAttribute("sches", sches);
        request.getRequestDispatcher("view/student/schedule.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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

}
