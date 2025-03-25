package app.com.constructionxpert.filters;

import app.com.constructionxpert.dtos.UserDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/auth/login.jsp"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("auth") != null) {
            UserDTO user = (UserDTO) session.getAttribute("auth");

            switch (user.getRole()) {
                case ADMIN:
                    res.sendRedirect( "/dashboard.jsp" );
                    break;

                case SUPPLIER:
                    res.sendRedirect( "/trainer/dashboard.jsp" );
                    break;

                case EMPLOYER_RESPONSIBLE:
                    res.sendRedirect( "/member/home.jsp" );
                    break;

                case EMPLOYER_MEMBER:
                    res.sendRedirect( "/member/home.jsp" );
                    break;
            }

            return;
        }

        chain.doFilter(request, response);
    }
}
