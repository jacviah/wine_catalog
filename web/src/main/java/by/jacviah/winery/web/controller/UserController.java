package by.jacviah.winery.web.controller;

import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.SecurityService;
import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.web.rq.CreateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.script.Bindings;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String registration() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "registration";
        }
        return "redirect:/home";
    }

    @PostMapping("/registration")
    public String registration(@Valid CreateUser rq, BindingResult result, ModelMap map) {
        String login = rq.getLogin();
        String password = rq.getPassword();
        if(result.hasErrors()) {
            map.addAttribute("error", "check your passwords");
            return "registration";
        }
        User user = service.createUser(login, password);
        if (user == null) {
            map.addAttribute("error", "user not created");
            return "registration";
        }
        log.info("user {} logged", user.getUsername());
        return "redirect:/home";
    }
}