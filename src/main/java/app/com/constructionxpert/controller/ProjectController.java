package app.com.constructionxpert.controller;

import app.com.constructionxpert.service.ProjectService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/project/*")
public class ProjectController extends HttpServlet {
    ProjectService projectService = new ProjectService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        switch (action) {
            // Projects
            case "/add.jsp":
                projectService.addProjectForm(req,resp);
                break;

            case "/add":
                projectService.addProject(req,resp);
                break;

            case "/search":
                projectService.searchProjects(req, resp);
                break;

            case "/edit.jsp":
                projectService.editProjectForm(req,resp);
                break;

            case "/edit":
                projectService.editProject(req,resp);
                break;

            case "/delete":
                projectService.deleteProject(req,resp);
                break;

            case "/list.jsp":
                projectService.listProjects(req,resp);
                break;

            default:
                resp.sendRedirect("list.jsp");

        }
    }
}
