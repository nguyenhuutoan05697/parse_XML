/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import toannh.student.StudentDAO;
import toannh.student.StudentDTO;

/**
 *
 * @author HuuToan
 */
@WebServlet(name = "SearchAddressServlet", urlPatterns = {"/SearchAddressServlet"})
public class SearchAddressServlet extends HttpServlet {
     private static final String HOME_PAGE = "index.html";
     private static final String SEARCH_PAGE = "search.jsp";
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
        String url = SEARCH_PAGE;
        String searchValue = request.getParameter("txtSearchValue");
        try {
            ServletContext context = this.getServletContext();
            Document document = (Document)context.getAttribute("DOMTREE");
            if(document == null){
            //call parse DOM again
            //store context
            }//end document did not exist
            //document has existed
            //1. Call Model - DAO
            StudentDAO dao = new StudentDAO();
            dao.searchAddress(searchValue, document);
            //2. Process Result
            List<StudentDTO> result = dao.getStudents();
            request.setAttribute("STUDENT", result);     
        } catch (XPathExpressionException e) {
            log("XPath: " + e.getMessage());
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
