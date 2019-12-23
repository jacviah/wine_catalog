package by.jacviah.winery.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlingController {
    @GetMapping(value = "/unauthorized")
    public String unauthorizHandle(Model model) {
        return "accessDenied";
    }

    @GetMapping(value = "/accessDenied")
    public String accessDeniedhandle(Model model) {
        return "accessDenied";
    }
}
