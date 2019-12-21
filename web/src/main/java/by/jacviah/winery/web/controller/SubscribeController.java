package by.jacviah.winery.web.controller;

import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.web.rq.SimpleUserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class SubscribeController {
    private static final Logger log = LoggerFactory.getLogger(SubscribeController.class);

    private final UserService service;

    public SubscribeController(UserService service) {
        this.service = service;
    }

    @GetMapping("/subscribe")
    public String subscribe(ModelMap map) {
        List<User> sommeliers = service.getUsersByRole(Role.SOMMELIER.toString());
        List<SimpleUserForm> result = sommeliers.stream().
                map(sommelier -> new SimpleUserForm(sommelier)).collect(Collectors.toList());
        map.addAttribute("sommeliers", result);
        return "subscribe";
    }

    @PostMapping("/subscribe/{id}")
    public String subscribe(@PathVariable long id, UsernamePasswordAuthenticationToken authentication,
                               ModelMap map) {
        service.subscribe(((User)authentication.getPrincipal()).getId(), id);
        map.addAttribute("message", "Ok, you are subscribed");
        return "subscribe";
    }
}
