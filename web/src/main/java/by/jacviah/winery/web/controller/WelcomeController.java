package by.jacviah.winery.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    @GetMapping(value = "/home")
    public String home(Model model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains("ROLE_SOMMELIER")) {
            return "redirect:/recomendation-list";
        }
        return "redirect:/bottle-list";
    }
}
