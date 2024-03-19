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
import jakarta.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        session.setAttribute("semesters", semesters);

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
        String raw_semid = request.getParameter("semid");
        int semid = 0;
        
        HttpSession session = request.getSession();
        
        if (raw_semid != null && !raw_semid.isEmpty()) {
            semid = Integer.parseInt(raw_semid);
            session.setAttribute("semid", semid);
        }
        SubjectDBContext subdb = new SubjectDBContext();

        String raw_subid = request.getParameter("subid");
        int subid = 0;
        if (raw_subid != null && !raw_subid.isEmpty()) {
            subid = Integer.parseInt(raw_subid);
        }
        GradeDBContext gdb = new GradeDBContext();
        ArrayList<Grade> grades = gdb.getGradeBySubject(subid);
        ArrayList<Subject> subjects = subdb.getSubjectBySemester(semid);
        
        float sumGrade = 0;
        
        for (Grade grade : grades) {
            float sumItem = grade.getWeight() * grade.getValue() / 100;
            sumGrade+= sumItem;
        }
        
        request.setAttribute("sumGrade", sumGrade);
        request.setAttribute("grades", grades);
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("view/student/viewgrade.jsp").forward(request, response);
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
