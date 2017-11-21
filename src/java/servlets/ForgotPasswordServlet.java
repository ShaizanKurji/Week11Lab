/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.AccountService;
import businesslogic.UserService;
import dataaccess.NotesDBException;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 715060
 */
public class ForgotPasswordServlet extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String emailAddress  = request.getParameter("emailAddress");
        String path = getServletContext().getRealPath("/WEB-INF/emailtemplates/login.html");
        
          UserService us = new UserService();
        User user;
        try {
            user = us.getByEmail(emailAddress);
            request.setAttribute("firstname", user.getFirstname());
            request.setAttribute("lastname", user.getLastname());
            request.setAttribute("username", user.getUsername());
            request.setAttribute("password", user.getPassword());
        } catch (NotesDBException ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AccountService as = new AccountService();
       boolean emailSent;
        try {
            emailSent = as.forgotPassword(emailAddress, path);
               if(emailSent){
           request.setAttribute("emailStatus", "Password sent to the email: " + emailAddress);
       }
       else {
           request.setAttribute("emailStatus", "Invalid account, email was not sent");
       }
        } catch (NotesDBException ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    
    }

}
