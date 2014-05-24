/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebastian
 */
@WebServlet(name = "CalcServlet", urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {

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
        
        XmlParser parser = new XmlParser();
        String [] urls = parser.leerUrls();
             
        CalcProxy calcProxy = new CalcProxy(urls);
        
        String pattern = request.getParameter("pattern");
        String result = "0";
        String [] result2 = null;
        
        if (!pattern.equals("")) {
            result = calcProxy.buscar(pattern);
            System.out.println(result);
        } else
        {
             result = calcProxy.listar();
             result2 = calcProxy.listarTxt().split("&");
             System.out.println(result2.toString());
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(result);
            if(result2 != null)
            for(int i = 0; i < result2.length;i++)
            {
            // out.println("<p><a href=\"http://localhost:8080/sgarci18WasServer/files/urls.txt\" download=\"http://localhost:8080/sgarci18WasServer/files/urls.txt\">urls</a></p>\n");
            out.println("<p><a href=\""+ calcProxy.uri+"/../"+ result2[i]+"\" download=\""+ calcProxy.uri+"/../"+ result2[i]+"\">"+ result2[i]+"</a></p>\n"); //+url del json, pero no logre parsearlo
            }
            out.println("<p><a href=\"index.html\" title=\"pag principal\">Volver</a></p>");
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
