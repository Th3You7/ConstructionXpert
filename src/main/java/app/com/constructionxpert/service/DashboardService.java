package app.com.constructionxpert.service;

import app.com.constructionxpert.dao.*;
import app.com.constructionxpert.dtos.AssignmentDTO;
import app.com.constructionxpert.dtos.UserDTO;
import app.com.constructionxpert.entity.Assignment;
import app.com.constructionxpert.enums.ProjectStatus;
import app.com.constructionxpert.enums.ResourceType;
import app.com.constructionxpert.enums.TaskStatus;
import app.com.constructionxpert.enums.UserRole;
import app.com.constructionxpert.util.Card;
import app.com.constructionxpert.util.ProjectCardUtil;
import app.com.constructionxpert.util.TaskCardUtil;
import app.com.constructionxpert.util.UserCardUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DashboardService {
    ProjectDAO projectDAO = new ProjectDAO();
    TaskDAO taskDAO = new TaskDAO();
    UserDAO userDAO = new UserDAO();
    AssignmentDAO assignmentDAO = new AssignmentDAO();
    ResourceDAO resourceDAO = new ResourceDAO();
    public void getDashboard(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        Set<Card> projectCards = new HashSet<>();
        Set<Card> taskCards = new HashSet<>();
        Set<Card> userCards = new HashSet<>();
        Set<Card> resourceCards = new HashSet<>();
        try {
            Set<Object[]> projectCardsSet = projectDAO.getProjectCountGroupedByStatus();
            Set<Object[]> taskCardsSet = taskDAO.getTaskCountGroupedByStatus();
            Set<Object[]> resourceCardsSet = resourceDAO.getAssignementCountGroupedByType();
            Set<UserDTO> users = userDAO.getUsersExceptAdmin();
            Set<Assignment> assignments = assignmentDAO.getAssignments();

            System.out.println(assignments.size());


            long responsibleCount = users.stream().filter(user -> user.getRole() == UserRole.EMPLOYER_RESPONSIBLE).count();
            long memberCount = users.stream().filter(user -> user.getRole() == UserRole.EMPLOYER_MEMBER).count();
            long suppliersCount = users.stream().filter(user -> user.getRole() == UserRole.SUPPLIER).count();


            for (UserRole role : Arrays.stream(UserRole.values()).filter(role -> role != UserRole.ADMIN).collect(Collectors.toSet())) {
                long count = 0;
                switch (role) {
                    case EMPLOYER_MEMBER:
                        count = memberCount;
                        break;

                    case EMPLOYER_RESPONSIBLE:
                        count = responsibleCount;
                        break;

                    case SUPPLIER:
                        count = suppliersCount;
                        break;
                }

                Card card = new Card(UserCardUtil.getColor(role), role, count);
                userCards.add(card);

            }
            for (Object[] cardArr : projectCardsSet) {
                ProjectStatus status = (ProjectStatus) cardArr[0];
                long count = (Long) cardArr[1];

                Card card = new Card(ProjectCardUtil.getColor(status), status, count, ProjectCardUtil.getIcon(status));
                System.out.println(card.getProjectStatus() + " " + card.getCount());
                projectCards.add(card);
            }
            for(Object[] row : taskCardsSet) {
                TaskStatus status = (TaskStatus) row[0];
                long count = (Long) row[1];

                Card card = new Card(TaskCardUtil.getColor(status), status, count, TaskCardUtil.getIcon(status));
                taskCards.add(card);

            }

            for(Object[] row : resourceCardsSet) {
                ResourceType resourceType = (ResourceType) row[0];
                long count = (Long) row[1];

                Card card = new Card(resourceType, count);
                System.out.println(card.getResourceType() + " " + card.getCount());

                resourceCards.add(card);
                System.out.println(resourceCards.size());
            }

//            Set<AssignmentDTO> assignments = assignmentDAO.getAssignments();

            System.out.println("hellllooo 1");
            req.setAttribute("projectCards", projectCards);
            req.setAttribute("taskCards", taskCards);
            req.setAttribute("userCards", userCards);
            req.setAttribute("resourceCards", resourceCards);
            req.setAttribute("assignments", assignments);

            for(Assignment assignment : assignments) {
                System.out.println(assignment.getEmployer().getFirstName());
                System.out.println(assignment.getTask().getProject().getTitle());

            }


        }catch(Exception e) {
            System.out.println(e.getMessage());
        }finally {
            req.getRequestDispatcher("/WEB-INF/views/admin/dashboard/index.jsp").forward(req, res);
        }
    }
}
