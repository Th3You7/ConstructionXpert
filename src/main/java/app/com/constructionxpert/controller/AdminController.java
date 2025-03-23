package app.com.constructionxpert.controller;

import app.com.constructionxpert.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
    AdminService adminService = new AdminService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        switch (action) {
            case "/add-project.jsp":
                adminService.addProjectForm(req,resp);
                break;

            case "/add-project":
                adminService.addProject(req,resp);
                break;

            case "/search-projects":
                adminService.searchProjects(req, resp);
                break;

            case "/edit-project.jsp":
                adminService.editProjectForm(req,resp);
                break;

            case "/edit-project":
                adminService.editProject(req,resp);
                break;

            case "/delete-project":
                adminService.deleteProject(req,resp);
                break;

            case "/projects.jsp":
                adminService.listProjects(req,resp);
                break;



        }
    }
}
