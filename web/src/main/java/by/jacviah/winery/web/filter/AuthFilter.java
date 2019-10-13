package by.jacviah.winery.web.filter;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.service.ServiceFactory;
import by.jacviah.winery.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.UUID;

@WebFilter(servletNames = "FindWineServlet")
public class AuthFilter implements Filter {
    //private FilterConfig filterConfig;

    public void init(final FilterConfig filterConfig) throws ServletException {
        //this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String errorMessage = "";
        User user = null;
        UUID cookieUuid = null;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                try {
                    if ("_name".equals(ck.getName())) {
                        user = service.findUser(ck.getValue());
                    }
                    if ("_uuid".equals(ck.getName())) {
                        cookieUuid = UUID.fromString(ck.getValue());
                    }

                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }

            if (user != null) {
                if (user.getUuid().equals(cookieUuid) & user.getRole() == Role.FREE_USER) {
                    filterChain.doFilter(req, resp);
                } else {
                    errorMessage = "You need to login again";
                }
            } else {
                errorMessage = "No such user found";
            }
            req.setAttribute("Error_Message", errorMessage);
            req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }
    }


    public void destroy() {

    }
}
