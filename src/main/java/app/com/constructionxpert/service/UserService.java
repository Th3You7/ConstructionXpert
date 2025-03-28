package app.com.constructionxpert.service;

import app.com.constructionxpert.dao.TaskDAO;
import app.com.constructionxpert.dao.UserDAO;
import app.com.constructionxpert.dtos.TaskDTO;
import app.com.constructionxpert.dtos.UserDTO;
import app.com.constructionxpert.entity.*;
import app.com.constructionxpert.enums.EmployerRole;
import app.com.constructionxpert.enums.UserRole;
import app.com.constructionxpert.exception.UserEmailNoExistException;
import app.com.constructionxpert.mapper.TaskMapper;
import app.com.constructionxpert.mapper.UserMapper;
import app.com.constructionxpert.util.Card;
import app.com.constructionxpert.util.UserCardUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserService {
    UserDAO userDAO = new UserDAO();
    TaskDAO taskDAO = new TaskDAO();

    public void listUsers(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Set<Card> cards = new HashSet<>();
        try {
            Set<UserDTO> users = userDAO.getUsersExceptAdmin();


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
               cards.add(card);

            }

            req.setAttribute("users", users);
            req.setAttribute("cards", cards);

        }catch (Exception e) {
            System.out.println("Something went wrong");

        }finally {
            req.getRequestDispatcher("/WEB-INF/views/admin/user/users.jsp").forward(req, res);
        }
    }
    public void getUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        try {
            User user = userDAO.getUserById(id);
            req.setAttribute("user", user);
        }catch (Exception e) {
            System.out.println("Something went wrong");
        }finally{
            req.getRequestDispatcher("/WEB-INF/views/admin/user.jsp");
        }
    }
    public void addUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String firstname = req.getParameter("firstName");
        String lastname = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(req.getParameter("role"));
        UserRole role = UserRole.valueOf(req.getParameter("role"));

        User user = role.equals(UserRole.SUPPLIER) ? new Supplier() : new Employer();

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        if(user instanceof Employer) {
            long taskId = Long.parseLong(req.getParameter("taskId"));

            Task task1 = taskDAO.getTaskById(taskId);
            //Task task1 = TaskMapper.INSTANCE.toEntity(task);
            Set<Assignment> assignments = new HashSet<>();

            EmployerRole employerRole = role == UserRole.EMPLOYER_MEMBER ? EmployerRole.MEMBER : EmployerRole.RESPONSIBLE;
             ((Employer) user).setEmployerRole(employerRole);

             // assignment
            Assignment assignment = new Assignment();
            assignment.setEmployer((Employer) user);
            assignment.setTask(task1);
            assignment.setStartDate(task1.getStartDate());
            assignment.setEndDate(task1.getEndDate());
            assignments.add(assignment);

            ((Employer) user).setAssignments(assignments);
        }

        try {
            userDAO.saveUser(user);
            session.setAttribute("message", "User added successfully");
            session.setAttribute("type", "success");
        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("users.jsp");
        }
    }
    public void addUserForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = req.getParameter("type");
        req.setAttribute("roles", Arrays.stream(UserRole.values()).filter(role -> role != UserRole.ADMIN).collect(Collectors.toSet()));
        req.setAttribute("tasks", taskDAO.getTasks());
        req.setAttribute("type", type);
        req.getRequestDispatcher("/WEB-INF/views/admin/user/form.jsp").forward(req, res);
    }
    public void editUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        HttpSession session = req.getSession(false);
        User user = userDAO.getUserById(id);

        String firstname = req.getParameter("firstName");
        String lastname = req.getParameter("lastName");
        String email = req.getParameter("email");

        boolean isTheSameEmail = email.equals(user.getEmail());

        if(user instanceof Employer) {
            ((Employer)user).setEmployerRole(EmployerRole.valueOf(req.getParameter("employerRole")));
        }

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);

        try {
            userDAO.updateUser(user, isTheSameEmail);
            session.setAttribute("message", "User edited successfully");
            session.setAttribute("type", "success");
        }catch (Exception e) {
            session.setAttribute("message", "Something went wrong");
            session.setAttribute("type", "error");
        }finally {
            res.sendRedirect("users.jsp");
        }

    }
    public void editUserForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        User user = userDAO.getUserById(id);
        UserDTO userDTO = UserMapper.INSTANCE.toDTO(user);

        req.setAttribute("roles", Arrays.stream(UserRole.values()).filter(role -> role != UserRole.ADMIN).collect(Collectors.toSet()));
        req.setAttribute("tasks", taskDAO.getAllTasks());
        req.setAttribute("user", userDTO);

        try {
            req.getRequestDispatcher("/WEB-INF/views/admin/user/form.jsp").forward(req, res);
        }catch (Exception e) {
//            req.setAttribute("message", "Something went wrong");
//            req.setAttribute("type", "error");

            System.out.println(e.getMessage());
            res.sendRedirect("users.jsp");
        }

    }
    public void deleteUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        try {
            User user = userDAO.getUserById(id);
            userDAO.deleteUser(user);
        }catch (Exception e) {
            req.setAttribute("message", "Something went wrong");
            req.setAttribute("type", "error");
        }finally {
            res.sendRedirect("users.jsp");
        }
    }
    public void searchUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Set<Card> cards = new HashSet<>();
        String name = req.getParameter("name");
        try {
            Set<UserDTO> users = userDAO.getAllUsersByName(name);
            System.out.println(users);

            long responsibleCount = users.stream().filter(user -> user.getRole() == UserRole.EMPLOYER_MEMBER).count();
            long memberCount = users.stream().filter(user -> user.getRole() == UserRole.EMPLOYER_RESPONSIBLE).count();
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
                cards.add(card);

            }

            req.setAttribute("users", users);
            req.setAttribute("cards", cards);

        }catch (Exception e) {
            System.out.println(e.getMessage());

        }finally {
            req.getRequestDispatcher("/WEB-INF/views/admin/user/users.jsp").forward(req, res);
        }
    }
    public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        try {
            UserDTO user = userDAO.getUser(email, password);
            session.setAttribute("auth", user);
            if(user.getRole() == UserRole.EMPLOYER_MEMBER || user.getRole() == UserRole.EMPLOYER_RESPONSIBLE) {
                res.sendRedirect("/home.jsp");
            }
            if(user.getRole() == UserRole.SUPPLIER) {
                res.sendRedirect("/supplier.jsp");
            }
            if(user.getRole() == UserRole.ADMIN) {
                res.sendRedirect("/dashboard.jsp");
            }
        }catch (UserEmailNoExistException e){
            session.setAttribute("message", e.getMessage());
            session.setAttribute("type", "error");
            res.sendRedirect( "/auth/login.jsp");

        }catch (Exception e) {
            session.setAttribute("message", "Email or password is incorrect");
            session.setAttribute("type", "error");
            res.sendRedirect("/auth/login.jsp");
        }
    }

    public void loginPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, res);
    }

    public void logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();

        }
        res.sendRedirect("/auth/login.jsp");
    }
}
