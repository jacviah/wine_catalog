package by.jacviah.winery.web.controller;

import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.web.rq.CreateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String registration(@Validated CreateUser rq, BindingResult result, ModelMap map) {
        if(result.hasErrors()) {
            log.info("create user errors: ", result.getAllErrors());
            return "registration";
        }
        String login = rq.getLogin();
        String password = rq.getPassword();
        boolean createResult = service.createUser(login, password);
        if (createResult == false) {
            map.addAttribute("error", "user not created");
            return "registration";
        }
        log.info("user {} logged", login);
        return "redirect:/login";
    }
}