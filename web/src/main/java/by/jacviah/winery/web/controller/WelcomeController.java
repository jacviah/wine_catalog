package by.jacviah.winery.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    @GetMapping(value = "/home")
    public String home(Model model) {
        return "home";
    }
}
