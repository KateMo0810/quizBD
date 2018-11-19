/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Escuela;
import modelo.daoEscuela;
import modelo.daoEstudiante;

/**
 *
 * @author Labing
 */
public class RegistroEscuelaServlet extends HttpServlet {
private daoEstudiante daoes;
private daoEscuela daoec;
    @Override
    public void init() throws ServletException {
        this.daoec= new daoEscuela();
        this.daoes= new daoEstudiante();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        
    try {
        RequestDispatcher rq = request.getRequestDispatcher("newjsp.jsp");
        ArrayList<Escuela> personas =(ArrayList) this.daoec.listar();
        request.setAttribute("lista", personas);
        rq.forward(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(RegistroEscuelaServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (URISyntaxException ex) {
        Logger.getLogger(RegistroEscuelaServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

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
        if(request.getParameter("Buscar")!=null){
            try {
                String nomEscuela = request.getParameter("escuela");
                daoes.listar(nomEscuela);
            } catch (SQLException ex) {
                Logger.getLogger(RegistroEscuelaServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(RegistroEscuelaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        response.sendRedirect("RegistroEscuelaServlet");

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
