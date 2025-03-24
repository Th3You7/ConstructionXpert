package app.com.constructionxpert.controller;

import app.com.constructionxpert.service.UserService;
import jakarta.persistence.Entity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        switch (action) {
            case "/add.jsp":
                userService.addUserForm(req, resp);
                break;

            case "/add":
                userService.addUser(req, resp);
                break;

            case "/edit.jsp":
                userService.editUserForm(req, resp);
                break;

            case "/edit":
                userService.editUser(req, resp);
                break;

            case "/delete":
                userService.deleteUser(req, resp);
                break;

            case "/search":
                userService.searchUser(req, resp);
                break;

            case "/list.jsp":
                userService.listUsers(req, resp);
                break;

            default:
                resp.sendRedirect("list.jsp");


        }
    }
}
