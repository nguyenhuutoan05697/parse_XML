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
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import toannh.student.StudentDAO;
import toannh.student.StudentDTO;

/**
 *
 * @author HuuToan
 */
@WebServlet(name = "CreateStudentServlet", urlPatterns = {"/CreateStudentServlet"})
public class CreateStudentServlet extends HttpServlet {
    private final String ERROR_PAGE = "createStudent.jsp";
    private final String LOGIN_PAGE = "index.html";
    private final String STATUS = "123";
    private final String PASSWORD = "456";
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
        String id = request.getParameter("txtId");
        String lastName = request.getParameter("txtLastname");
        String middleName = request.getParameter("txtMiddlename");
        String firstName = request.getParameter("txtFirstname");
        String male = request.getParameter("chkMale");
        if(male == null){
            male = "0";
        }
        String address = request.getParameter("txtAddress");
        String sClass = request.getParameter("txtClass");
              
        try {//validattion input by yourself
            
            ServletContext context = this.getServletContext();
            Document document = (Document) context.getAttribute("DOMTREE");
            if (document == null) {
               //Call Parse DOM Again
                //update context scope
            }//end document is not existed
            //DOMTREE has ben existed
            //2. Call DAO
            String xmlFilePath = context.getInitParameter("STUDENTACCOUNT_XML_PATH");
            String realPath = context.getRealPath("/");
            String xmlFile = realPath + xmlFilePath;
            StudentDTO dto = new StudentDTO(id, sClass, firstName, address, male, STATUS, firstName, middleName, lastName, PASSWORD);
            StudentDAO dao = new StudentDAO();
            boolean result = dao.createStudent(dto, document, xmlFile);
            //3.Process result
            if(result){
                url = LOGIN_PAGE;
            }
        } catch (TransformerException e) {
            log("Error at CreateStudentServlet: " + e.getMessage());
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
