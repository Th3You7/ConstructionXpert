package app.com.constructionxpert.controller;

import app.com.constructionxpert.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/task/*")
public class TaskController extends HttpServlet {
    TaskService taskService = new TaskService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        switch (action) {
            case "/add.jsp":
                taskService.addTaskForm(req, resp);
                break;

            case "/add":
                taskService.addTask(req, resp);
                break;

            case "/edit.jsp":
                taskService.updateTaskForm(req, resp);
                break;

            case "/edit":
                taskService.updateTask(req, resp);
                break;

            case "/list.jsp":
                taskService.listTasks(req, resp);
                break;

            case "/delete":
                taskService.deleteTask(req, resp);
                break;

            case "/search":
                taskService.searchTasks(req, resp);
                break;

            case "/":
                taskService.getTask(req, resp);
                break;


            default:
                resp.sendRedirect("list.jsp");

        }
    }
}
