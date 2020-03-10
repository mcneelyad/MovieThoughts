package Controllers;

import JavaBeans.*;
import Database.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//java validation imports 
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
        HttpSession session = request.getSession();
        UserProfile profile = (UserProfile) session.getAttribute("profile");
        String url = profile == null ? "/login.jsp" : "/myItems.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
        String url = "/myItems.jsp";

        try {
            String email = ESAPI.validator().getValidInput("replace ME with validation context",
                    request.getParameter("email"), "Email", 200, false);
            String password = ESAPI.validator().getValidInput("replace ME with validation context",
                    request.getParameter("action"), "SafeString", 200, false);

            if (UserDB.validateLogin(email, password)) {
                HttpSession session = request.getSession(true);
                UserProfile profile = UserDB.getUserProfile(email);

                session.setAttribute("user", profile.getItems());

            } // Login info didn't match record in DB
            else {
                url = "/login.jsp";
                request.setAttribute("message", "Sorry, that username/password didn't match our records.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
