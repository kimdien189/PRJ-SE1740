/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.GalleryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kimdi
 */
public class changeLike extends HttpServlet {

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
            // Get the image ID from the form data
            String imageId = request.getParameter("imageId");

            // Check whether the user has already liked the image
            boolean hasLiked = false;
            HttpSession session = request.getSession(false);
            if (session != null) {
                @SuppressWarnings("unchecked")
                Set<String> likedImageIds = (Set<String>) session.getAttribute("likedImageIds");
                if (likedImageIds != null && likedImageIds.contains(imageId)) {
                    hasLiked = true;
                }
            }

            // Update the like count in the database
            GalleryDAO galleryDAO = new GalleryDAO();
            if (hasLiked) {
                int likes = galleryDAO.decrementLikeCount(imageId);
                if (session != null) {
                    @SuppressWarnings("unchecked")
                    Set<String> likedImageIds = (Set<String>) session.getAttribute("likedImageIds");
                    if (likedImageIds != null) {
                        likedImageIds.remove(imageId);
                    }
                    session.setAttribute("likedImageIds", likedImageIds);
                }
            } else {
                int likes = galleryDAO.incrementLikeCount(imageId);
                if (session != null) {
                    @SuppressWarnings("unchecked")
                    Set<String> likedImageIds = (Set<String>) session.getAttribute("likedImageIds");
                    if (likedImageIds == null) {
                        likedImageIds = new HashSet<>();
                    }
                    likedImageIds.add(imageId);
                    session.setAttribute("likedImageIds", likedImageIds);
                }
            }
            session.setAttribute("likes", galleryDAO.getImageLike(imageId));
            out.print(galleryDAO.getImageLike(imageId));
            out.flush();
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
