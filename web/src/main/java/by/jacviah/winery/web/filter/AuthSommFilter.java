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
import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = "/sommelierview/*")
public class AuthSommFilter implements Filter {

    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

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
                if (user.getUuid().equals(cookieUuid) & user.getRole() == Role.SOMMELIER) {
                    req.getSession().setAttribute("user_id", user.getId());
                    filterChain.doFilter(req, resp);
                }
            }
            resp.sendRedirect("/login");

        }
    }


    public void destroy() {

    }
}
