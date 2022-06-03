/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import toannh.student.StudentDAO;
import toannh.student.StudentDTO;

/**
 *
 * @author HuuToan
 */
@WebServlet(name = "UpdateStudentServlet", urlPatterns = {"/UpdateStudentServlet"})
public class UpdateStudentServlet extends HttpServlet {
    private static final String ERROR_PAGE = "invalid.html";
    private static final String SUCCESS = "search.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        String id = request.getParameter("id");
        String sClass = request.getParameter("sClass");
        String address = request.getParameter("address");
        String sex = request.getParameter("sex");
        String status = request.getParameter("status");
        String fullName = request.getParameter("fullName");
        String searchValue = request.getParameter("lastSearchValue");
        try {
            ServletContext context = request.getServletContext();
            Document document = (Document) context.getAttribute("DOMTREE");
            if(document != null){
                
            }
            StudentDAO dao = new StudentDAO();
            StudentDTO dto = new StudentDTO(id, sClass, fullName, address, sex, status);
            
            String xmlFilePath = context.getInitParameter("STUDENTACCOUNT_XML_PATH");
            String realPath = context.getRealPath("/");
            String xmlFile = realPath + xmlFilePath;
            boolean result = dao.updateStudent(dto, document, xmlFile);
            if(result){
                 url = "DispatchController"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }
        } catch (Exception e) {
            log("Error at UpdateStudentServlet: " + e.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
