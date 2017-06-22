/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.pmdapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.ittepic.pmdapp.ejbs.EjbPmd;

/**
 *
 * @author Esteban
 */
@WebServlet(name = "InsertarCategoriaplan", urlPatterns = {"/InsertarCategoriaplan"})
public class InsertarCategoriaplan extends HttpServlet {
    @EJB
    private EjbPmd ejb;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertarCategoriaplan</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertarCategoriaplan at " + request.getContextPath() + "</h1>");
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
        
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");
        PrintWriter p = response.getWriter();
        
        String clave = request.getParameter("clavecat");
        String nombre = request.getParameter("nombrecat");
        String descrip = request.getParameter("descripcat");
        try{
            //int idcatpadre = Integer.parseInt(request.getParameter("idcatpadre"));
            String sIdcatpadre = request.getParameter("idcatpadre");
            int idcatpadre = sIdcatpadre != null? Integer.parseInt(sIdcatpadre):0;
            int idplan = Integer.parseInt(request.getParameter("idplan"));
            
            p.write(ejb.insertarCategoriaplan(clave, nombre, idcatpadre, idplan, descrip));
        }catch(NumberFormatException e){
            p.write("{msg:'ERROR: Los ID de Categoría Padre o del Plan, deben ser numéricos.'}");
        }
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
