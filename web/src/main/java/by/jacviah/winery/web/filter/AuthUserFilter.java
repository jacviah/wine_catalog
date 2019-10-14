package by.jacviah.winery.web.filter;

import by.jacviah.winery.dao.exception.DaoException;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.ServiceFactory;
import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.web.WebUtils;

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
                    if ("name".equals(ck.getName())) {
                        user = service.findUser(URLDecoder.decode(ck.getValue(), "UTF-8"));
                    }
                    if ("uuid".equals(ck.getName())) {
                        cookieUuid = UUID.fromString(URLDecoder.decode(ck.getValue(), "UTF-8"));
                    }

                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }

            if (user != null) {
                if (user.getUuid().equals(cookieUuid) & user.getRole() == Role.USER) {
                    req.getSession().setAttribute("user_id", user.getId());
                    req.getSession().setAttribute("user_name", user.getUsername());
                    filterChain.doFilter(req, resp);
                } else {
                    WebUtils.redirect("/login", req, resp);
                }
            } else {
                WebUtils.redirect("/login", req, resp);
            }
        }
    }


    public void destroy() {

    }
}
