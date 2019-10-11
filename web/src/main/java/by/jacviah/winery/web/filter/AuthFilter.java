package by.jacviah.winery.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import by.jacviah.winery.service.ServiceFactory;
import by.jacviah.winery.service.UserService;

@WebFilter(urlPatterns = "/winery")
public class AuthFilter implements Filter {
    private FilterConfig filterConfig;

    public void init(final FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("_user".equals(ck.getName()) &
                        ck.getValue().equals(service.getUserUUID(ck.getName()))) {
                    filterChain.doFilter(req, resp);
                } else {
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            }
        }
    }

    public void destroy() {

    }
}
