package by.jacviah.winery.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping(value = "/")
    public String home(Model model) {
        return "home";
    }
}
