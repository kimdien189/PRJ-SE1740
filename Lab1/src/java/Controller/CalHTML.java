/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author AD
 */
public class CalHTML extends HttpServlet {

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
    try ( PrintWriter out = response.getWriter()) {
        /* TODO output your page here. You may use following sample code. */
        String fStr = request.getParameter("first");
        String sStr = request.getParameter("second");
        String o = request.getParameter("oper");

        double f = Double.parseDouble(fStr);
        double s = Double.parseDouble(sStr);
        double result = 0;

        switch (o) {
            case "+":
                result = f + s;
                break;
            case "-":
                result = f - s;
                break;
            case "*":
                result = f * s;
                break;
            case "/":
                result = f / s;
                break;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Caculator</title>");
        out.println("");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> Caculator simple </h1>");
        out.println("<form action=\"CalHTML\">\n"
                + "            First:  \n"
                + "            <input type=\"text\" name=\"first\" value=\"" + f + "\"/><br/>\n"
                + "            Second: \n"
                + "            <input type=\"text\" name=\"second\" value=\"" + s + "\"/><br/>\n"
                + "            Operator\n"
                + "            <select name=\"oper\" value=\"" + o + "\">\n"
                + "                <option>+</option>\n"
                + "                <option>-</option>\n"
                + "                <option>*</option>\n"
                + "                <option>/</option>\n"
                + "            </select><br/>\n"
                + "            <input type=\"submit\" name=\"submit\" value=\"Compute\"/><br/>\n");

        if (o != null) {
            out.println("            Result: <input type=\"text\" name=\"result\" value=\"" + result + "\"/>\n"
                    + "        </form>");
        } else {
            out.println("            Result: <input type=\"text\" name=\"result\"/>\n"
                    + "        </form>");
        }
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
