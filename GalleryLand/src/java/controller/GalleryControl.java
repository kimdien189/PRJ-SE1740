/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.GalleryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginSession</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginSession at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        GalleryDAO galleryDAO = new GalleryDAO();
        List<String> ids = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        StringJoiner joiner = new StringJoiner(",");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(EXCLUDED_IDS_COOKIE_NAME)) {
                    String[] idStrings = cookie.getValue().split(",");
                    for (String idString : idStrings) {
                        ids.add(idString);
                    }
                    break;
                }
            }
            try {
                List<Gallery> images = galleryDAO.getRandomImages(ids);
                List<String> excludedIds = images.stream().map(galleryImg -> galleryImg.getID()).collect(Collectors.toList());
                for (String id : excludedIds) {
                    if (!ids.contains(id)) {
                        ids.add(id);
                        joiner.add(id);
                    }
                }
                Cookie cookie = new Cookie(EXCLUDED_IDS_COOKIE_NAME, joiner.toString());
                cookie.setMaxAge(3600); // 1 hour
                cookie.setPath("/");
                response.addCookie(cookie);
                // Set the images as a request attribute
                System.out.println(images.size());
                request.setAttribute("images", images);
                // Forward the request to the index.jsp file
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GalleryControl.class.getName()).log(Level.SEVERE, null, ex);
            }

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
