package by.jacviah.winery.filter;

import by.jacviah.winery.exception.DaoException;
import by.jacviah.winery.Role;
import by.jacviah.winery.User;
import by.jacviah.winery.ServiceFactory;
import by.jacviah.winery.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.UUID;

@WebFilter(urlPatterns = "/userview/*")
public class AuthUserFilter implements Filter {

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
                        user = service.findUser(URLDecoder.decode(ck.getValue(), "UTF-8"));
                    }
                    if ("_uuid".equals(ck.getName())) {
                        cookieUuid = UUID.fromString(URLDecoder.decode(ck.getValue(), "UTF-8"));
                    }

                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }

            if (user != null) {
                if (user.getUuid().equals(cookieUuid) & user.getRole() == Role.USER) {
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
