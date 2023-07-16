/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.AccountDAO;
import DAL.GalleryDAO;
import DAL.CommentDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Gallery;
import model.Comment;
import model.Account;

/**
 *
 * @author macbookair
 */
public class imageVisitControl extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            GalleryDAO galleryDAO = new GalleryDAO();
            AccountDAO accountDAO = new AccountDAO();
            CommentDAO commentDAO = new CommentDAO();
            int image_ID = Integer.parseInt(request.getParameter("image_ID"));
            Gallery image = galleryDAO.getImageByID(image_ID);
            List<Comment> comments = commentDAO.getCommentsByID(image_ID);
            List<Account> accounts = new ArrayList<>();
            for (Comment comment : comments) {
                int User_id = comment.getUser_id();
                Account account = accountDAO.getAccountInfoByID(User_id);
                accounts.add(account);
            }
            session.setAttribute("image", image);
            session.setAttribute("comments", comments);
            session.setAttribute("accounts", accounts);
            request.getRequestDispatcher("imageVisit.jsp").forward(request, response);
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

    /*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean[] tags1 = {true, false, true, false, false, true, false, true, false, true, false, false, true, false, true};
        boolean[] tags2 = {false, true, false, true, false, true, false, true, true, false, true, false, false, true, false};
        boolean[] tags3 = {true, true, true, false, true, false, true, false, false, true, false, true, false, true, false};
        List<Gallery> images = new ArrayList<>();
        images.add(new Gallery("1", "https://drive.google.com/uc?id=1nZmGeXP1uleF8i3mLO4FIa8FuGp3yKdB", "Gallery 1", "John Doe", new Date(System.currentTimeMillis()), 10, tags1));
        images.add(new Gallery("1", "https://drive.google.com/uc?id=1nZmGeXP1uleF8i3mLO4FIa8FuGp3yKdB", "Gallery 1", "John Doe", new Date(System.currentTimeMillis()), 10, tags1));
        images.add(new Gallery("1", "https://drive.google.com/uc?id=1nZmGeXP1uleF8i3mLO4FIa8FuGp3yKdB", "Gallery 1", "John Doe", new Date(System.currentTimeMillis()), 10, tags1));
        request.setAttribute("images", images);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
     */
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
