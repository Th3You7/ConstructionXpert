package app.com.constructionxpert.service;

import app.com.constructionxpert.dao.ProjectDAO;
import app.com.constructionxpert.dtos.ProjectDTO;
import app.com.constructionxpert.entity.Employer;
import app.com.constructionxpert.entity.Project;
import app.com.constructionxpert.enums.ProjectStatus;
import app.com.constructionxpert.enums.ResourceType;
import app.com.constructionxpert.mapper.ProjectMapper;
import app.com.constructionxpert.util.ProjectCardUtil;
import app.com.constructionxpert.util.Card;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ProjectService {
    ProjectDAO projectDAO = new ProjectDAO();

    public void addProjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/project/form.jsp").forward(request, response);
    }
    public void editProjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        try {
           ProjectDTO project = projectDAO.getProjectById(id);
           request.setAttribute("project", project);
           request.getRequestDispatcher("/WEB-INF/views/admin/project/form.jsp").forward(request, response);
        }catch (Exception e) {
            response.sendRedirect("list.jsp");
        }
    }
    public void addProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        ProjectStatus status = ProjectStatus.PLANNING;

        Project project = new Project();
        project.setTitle(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setProjectStatus(status);

        HttpSession session = request.getSession();
        try {
            projectDAO.addProject(project);
            session.setAttribute("message", "Project added successfully");
            session.setAttribute("type", "success");
        }catch (Exception e) {
            session.setAttribute("message", "Error adding project");
            session.setAttribute("type", "error");
        }finally {
            response.sendRedirect("list.jsp");
        }
    }
    public void editProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        ProjectDTO project = projectDAO.getProjectById(id);
        Project project1 = ProjectMapper.INSTANCE.toEntity(project);
        HttpSession session = request.getSession(false);

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

        project1.setTitle(title);
        project1.setDescription(description);
        project1.setStartDate(startDate);
        project1.setEndDate(endDate);


        try {
            projectDAO.updateProject(project1);
            session.setAttribute("message", "Project updated successfully");
            session.setAttribute("type", "success");
        }catch (Exception e) {
            session.setAttribute("message", "Error updating project");
            session.setAttribute("type", "error");
        }finally {
            response.sendRedirect("list.jsp");
        }


    }
    public void deleteProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        HttpSession session = request.getSession(false);
        try {
            projectDAO.deleteProject(id);
            session.setAttribute("message", "Project deleted successfully");
            session.setAttribute("type", "success");
        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            response.sendRedirect("list.jsp");
        }
    }
    public void listProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Card> cards = new HashSet<>();
        try {
            Set<ProjectDTO> projects = projectDAO.getAllProjects();
            Set<Object[]> cardsSet = projectDAO.getProjectCountGroupedByStatus();

            for (Object[] cardArr : cardsSet) {
                ProjectStatus status = (ProjectStatus) cardArr[0];
                long count = (Long) cardArr[1];

                Card card = new Card(ProjectCardUtil.getColor(status), status, count, ProjectCardUtil.getIcon(status));

                cards.add(card);
            }

            request.setAttribute("projects", projects);
            request.setAttribute("cards", cards);

        }catch (Exception e) {
            System.out.println("Something went wrong");
        }finally {
           request.getRequestDispatcher("/WEB-INF/views/admin/project/projects.jsp").forward(request, response);

        }
    }
    public void searchProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Card> cards = new HashSet<>();
        String name = request.getParameter("name");
        try {
            Set<ProjectDTO> projects = projectDAO.searchProjectsByName(name);
            Set<Object[]> cardsSet = projectDAO.getProjectCountGroupedByStatus();

            for (Object[] cardArr : cardsSet) {
                ProjectStatus status = (ProjectStatus) cardArr[0];
                long count = (Long) cardArr[1];

                Card card = new Card(ProjectCardUtil.getColor(status), status, count, ProjectCardUtil.getIcon(status));

                cards.add(card);
            }

            request.setAttribute("projects", projects);
            request.setAttribute("cards", cards);

        }catch (Exception e) {
            System.out.println("Something went wrong");
        }finally {
            request.getRequestDispatcher("/WEB-INF/views/admin/project/projects.jsp").forward(request, response);

        }
    }
    public void getProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        HttpSession session = request.getSession(false);
        Set<Card> resourceCards = new HashSet<>();


        try {
            ProjectDTO project = projectDAO.getProjectById(id);
            Set<Employer> employers = projectDAO.getProjectEmployers(id);
            //Set<Object[]> cardsSet = projectDAO.getProjectAllocatedResourcesGroupedByResourceType(id);
            //System.out.println(cardsSet.size());

//            for(Object[] row : cardsSet) {
//                ResourceType resourceType = (ResourceType) row[0];
//                long count = (Long) row[1];
//
//
//                Card card = new Card(resourceType, count);
//
//                resourceCards.add(card);
//            }


            request.setAttribute("tasks", project.getTasks());
            request.setAttribute("employers", employers);
            request.setAttribute("cards", resourceCards);
        }catch (Exception e) {
            System.out.println("Something went wrong");
        }finally {
            request.getRequestDispatcher("/WEB-INF/views/admin/project/project.jsp").forward(request, response);
        }
    }
}
