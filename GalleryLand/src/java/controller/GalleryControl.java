/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.GalleryDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import model.Gallery;

/**
 *
 * @author macbookair
 */
public class GalleryControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String EXCLUDED_IDS_COOKIE_NAME = "excludedIds";

    private String joinExcludedIds(List<String> ids) {
        StringJoiner joiner = new StringJoiner(",");
        for (String id : ids) {
            joiner.add(id.toString());
        }
        return joiner.toString();
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            GalleryDAO galleryDAO = new GalleryDAO();
            List<String> ids = new ArrayList<>();
            StringJoiner joiner = new StringJoiner(",");

            try {
                List<Gallery> images = galleryDAO.getRandomImages(ids);
                List<String> excludedIds = images.stream().map(galleryImg -> galleryImg.getID()).collect(Collectors.toList());
                for (String id : excludedIds) {
                    if (!ids.contains(id)) {
                        ids.add(id);
                        joiner.add(id);
                    }
                }

                String encodedValue = URLEncoder.encode(joiner.toString(), "UTF-8");
                Cookie cookie = new Cookie(EXCLUDED_IDS_COOKIE_NAME, encodedValue);
                cookie.setMaxAge(3600); // 1 hour
                response.addCookie(cookie);
                // Set the images as a request attribute
                request.setAttribute("images", images);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                //response.sendRedirect("index.jsp");

                // Forward the request to the index.jsp file
            } catch (SQLException ex) {
                Logger.getLogger(GalleryControl.class.getName()).log(Level.SEVERE, null, ex);
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
