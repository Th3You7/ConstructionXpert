package app.com.constructionxpert.controller;

import app.com.constructionxpert.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/*")
public class AuthController extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        switch (action){
            case "/login.jsp":
                userService.loginPage(req, resp);
                break;

            case "/login":
                userService.login(req, resp);
                break;

            case "/logout":
                userService.logout(req, resp);
                break;
            default:
                resp.sendRedirect("login.jsp");
        }
    }
}
