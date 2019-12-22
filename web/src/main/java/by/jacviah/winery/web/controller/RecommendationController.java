package by.jacviah.winery.web.controller;

import by.jacviah.winery.model.Recommendation;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.sevice.RecommendService;
import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.sevice.WineService;
import by.jacviah.winery.web.rq.SimpleUserForm;
import by.jacviah.winery.web.rq.WineForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RecommendationController {
    private static final Logger log = LoggerFactory.getLogger(SubscribeController.class);

    private final UserService userService;
    private final RecommendService recService;
    private final WineService wineService;

    public RecommendationController(UserService userService, RecommendService recService, WineService wineService) {
        this.userService = userService;
        this.recService = recService;
        this.wineService = wineService;
    }

    @GetMapping("/subscribers")
    public String getSubscribersAndWines(UsernamePasswordAuthenticationToken authentication, ModelMap map) {
        List<User> subscribers = userService.getUsersBySommelier(((User) authentication.getPrincipal()).getId());
        List<SimpleUserForm> result = subscribers.stream().
                map(subscriber -> new SimpleUserForm(subscriber)).collect(Collectors.toList());
        map.addAttribute("subscribers", result);
        return "subscribers";
    }

    @GetMapping("/subscribers/{user}")
    public String getSubscribersAndWines(@PathVariable String user, ModelMap map) {
        List<Wine> wines = wineService.getWines();
        List<WineForm> result = wines.stream().map(wine -> new WineForm(wine)).collect(Collectors.toList());
        map.addAttribute("username", user);
        map.addAttribute("wines", result);
        return "recommendation";
    }

    @PostMapping("/recommendation")
    public String createRecommendation(@RequestParam String username,
                                       @RequestParam String description,
                                       @RequestParam Set<WineForm> wine,
                                       UsernamePasswordAuthenticationToken authentication,
                                       ModelMap map)  {
        Set<Wine> wines = new HashSet<>()/*= wineForms.stream()
                .map(wineForm -> wineService.findWine(wineForm.getName(), wineForm.getWinery()))
                .collect(Collectors.toSet())*/;
        Recommendation rec = Recommendation.RecommendationBuilder.aRecommendation()
                .withMessage(description)
                .withSommelier(((User) authentication.getPrincipal()))
                .withSubscriber(userService.findUser(username))
                .withWines(wines)
                .build();
        recService.createRecommendation(rec);
        map.addAttribute("message", "Ok, " + username + "must receive your recommendation");
        return "recommendation";
    }
}
