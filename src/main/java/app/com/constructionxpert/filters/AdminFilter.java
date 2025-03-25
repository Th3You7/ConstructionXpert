package app.com.constructionxpert.filters;

import app.com.constructionxpert.dtos.UserDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/dashboard.jsp", "/user/*", "/project/*", "/task/*"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("auth") == null) {
            response.sendRedirect("/auth/login.jsp");
        } else {
            UserDTO userDTO = (UserDTO) session.getAttribute("auth");
            switch (userDTO.getRole()) {
                case ADMIN:
                    chain.doFilter(request, response);
                    break;

                case SUPPLIER:
                    response.sendRedirect("/trainer/dashboard.jsp");
                    break;

                case EMPLOYER_RESPONSIBLE:
                    response.sendRedirect("/member/home.jsp");
                    break;

                case EMPLOYER_MEMBER:
                    response.sendRedirect("/member/home.jsp");
                    break;
            }
        }


    }
}
