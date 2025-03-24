package app.com.constructionxpert.service;

import app.com.constructionxpert.dao.ProjectDAO;
import app.com.constructionxpert.dao.TaskDAO;
import app.com.constructionxpert.dtos.ProjectDTO;
import app.com.constructionxpert.dtos.TaskDTO;
import app.com.constructionxpert.entity.Project;
import app.com.constructionxpert.entity.Task;
import app.com.constructionxpert.enums.TaskStatus;
import app.com.constructionxpert.mapper.ProjectMapper;
import app.com.constructionxpert.mapper.TaskMapper;
import app.com.constructionxpert.util.Card;
import app.com.constructionxpert.util.TaskCardUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TaskService {
    TaskDAO taskDAO = new TaskDAO();
    ProjectDAO projectDAO = new ProjectDAO();

    public void addTaskForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("projects", projectDAO.getAllProjects());
        request.getRequestDispatcher("/WEB-INF/views/admin/task/form.jsp").forward(request, response);
    }
    public void addTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        TaskStatus status = TaskStatus.IN_PROGRESS;
        long projectId = Long.parseLong(request.getParameter("projectId"));

        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setStatus(status);

        try {
            ProjectDTO project = projectDAO.getProjectById(projectId);
            Project project1 = ProjectMapper.INSTANCE.toEntity(project);
            task.setProject(project1);
            taskDAO.addTask(task);
            session.setAttribute("message", "Task added successfully");
            session.setAttribute("type", "success");
        }catch (Exception e) {

            session.setAttribute("message", "Error adding task");
            session.setAttribute("type", "error");

        }finally {
            response.sendRedirect("list.jsp");
        }


    }

    public void updateTaskForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long taskId = Long.parseLong(request.getParameter("id"));
        try {
            TaskDTO task = taskDAO.getTask(taskId);
            request.setAttribute("task", task);
            request.setAttribute("projects", projectDAO.getAllProjects());
            request.getRequestDispatcher("/WEB-INF/views/admin/task/form.jsp").forward(request, response);
        }catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("list.jsp");
        }
        System.out.println("updateTaskForm");
    }

    public void updateTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        long projectId = Long.parseLong(request.getParameter("projectId"));

        TaskDTO task = taskDAO.getTask(id);
        Task task1 = TaskMapper.INSTANCE.toEntity(task);
        HttpSession session = request.getSession(false);

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

        try {

            Project project = ProjectMapper.INSTANCE.toEntity(projectDAO.getProjectById(projectId));

            task1.setTitle(title);
            task1.setDescription(description);
            task1.setStartDate(startDate);
            task1.setEndDate(endDate);
            task1.setProject(project);

            taskDAO.updateTask(task1);
            session.setAttribute("message", "Task updated successfully");
            session.setAttribute("type", "success");

        }catch(Exception e) {
            session.setAttribute("message", "Error updating task");
            session.setAttribute("type", "error");

        }finally {
            response.sendRedirect("list.jsp");
        }

    }

    public void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        HttpSession session = request.getSession(false);

        try {
            taskDAO.deleteTask(id);

            session.setAttribute("message", "Task deleted successfully");
            session.setAttribute("type", "success");
        }catch (Exception e) {
            System.out.println(e.getMessage());

            session.setAttribute("message", "Error deleting task");
            session.setAttribute("type", "error");
        }finally {
            response.sendRedirect("list.jsp");
        }

    }

    public void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Card> cards = new HashSet<>();

        try {
            Set<TaskDTO> tasks = taskDAO.getAllTasks();
            Set<Object[]> cardsSet = taskDAO.getTaskCountGroupedByStatus();
            for(Object[] row : cardsSet) {
                TaskStatus status = (TaskStatus) row[0];
                long count = (Long) row[1];

                Card card = new Card(TaskCardUtil.getColor(status), status, count, TaskCardUtil.getIcon(status));
                cards.add(card);
            }
            request.setAttribute("cards", cards);
            request.setAttribute("tasks", tasks);
        }catch (Exception e) {
            System.out.println("Something went wrong");
        }finally {
            request.getRequestDispatcher("/WEB-INF/views/admin/task/tasks.jsp").forward(request, response);
        }

        // here

    }

    public void searchTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        Set<Card> cards = new HashSet<>();

        try {
            Set<TaskDTO> tasks = taskDAO.searchProjectByName(name);
            Set<Object[]> cardsSet = taskDAO.getTaskCountGroupedByStatus();
            for(Object[] row : cardsSet) {
                TaskStatus status = (TaskStatus) row[0];
                long count = (Long) row[1];

                Card card = new Card(TaskCardUtil.getColor(status), status, count, TaskCardUtil.getIcon(status));
                cards.add(card);
            }
            request.setAttribute("cards", cards);
            request.setAttribute("tasks", tasks);
        }catch (Exception e) {
            System.out.println("Something went wrong");
        }finally {
            request.getRequestDispatcher("/WEB-INF/views/admin/task/tasks.jsp").forward(request, response);
        }
    }


}
