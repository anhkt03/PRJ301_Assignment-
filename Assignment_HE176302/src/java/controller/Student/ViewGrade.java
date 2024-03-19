/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Student;

import dal.DepartmentDBContext;
import dal.GradeDBContext;
import dal.SemesterDBContext;
import dal.SubjectDBContext;
import entity.Department;
import entity.Grade;
import entity.Semester;
import entity.Subject;
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
@WebServlet(name = "ViewGrade", urlPatterns = {"/grade"})
public class ViewGrade extends HttpServlet {

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
            out.println("<title>Servlet ViewGrade</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewGrade at " + request.getContextPath() + "</h1>");
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
        SemesterDBContext sdb = new SemesterDBContext();
        ArrayList<Semester> semesters = sdb.list();
        
        String raw_semid = request.getParameter("semid");
        int semid = 0;
        if(raw_semid != null) {
            semid = Integer.parseInt(raw_semid);
        }
        SubjectDBContext subdb = new SubjectDBContext();
        
        
        String raw_subid = request.getParameter("subid");
        int subid = 0;
        if(raw_subid != null) {
            subid = Integer.parseInt(raw_subid);
        }
        GradeDBContext gdb = new GradeDBContext();
        ArrayList<Grade> grades = gdb.getGradeBySubject(subid);
        
        Semester sem = sdb.getTermBySubid(subid);
        
        ArrayList<Subject> subjects = subdb.getSubjectBySemester(semid);
        request.setAttribute("grades", grades);
        request.setAttribute("subjects", subjects);
        request.setAttribute("semesters", semesters);
        request.getRequestDispatcher("view/student/viewgrade.jsp").forward(request, response);
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
