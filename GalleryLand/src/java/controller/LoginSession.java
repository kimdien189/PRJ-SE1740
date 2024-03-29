/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author macbookair
 */
public class LoginSession extends HttpServlet {

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
            HttpSession session = request.getSession();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AccountDAO db = new AccountDAO();
            Account account = db.getAccountByUsernameAndPassword(username, password);
            if (account != null) // login successful!
            {//remembe me
                String remember = request.getParameter("remember");
                if (remember != null) {
                    Cookie c_user = new Cookie("username", username);
                    Cookie c_pass = new Cookie("password", password);
                    Cookie c_detail = new Cookie("detail", account.getDisplayname());
                    c_user.setMaxAge(3600 * 24 * 30);
                    c_pass.setMaxAge(3600 * 24 * 30);
                    c_pass.setMaxAge(3600 * 24 * 30);
                    response.addCookie(c_pass);
                    response.addCookie(c_user);
                    response.addCookie(c_detail);
                }
                request.setAttribute("account", account);
                request.setAttribute("loginStatus", "success");
                request.getRequestDispatcher("home").forward(request, response);
            } else //login fail
            {
                request.setAttribute("account", account);
                request.setAttribute("loginStatus", "fail");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
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
