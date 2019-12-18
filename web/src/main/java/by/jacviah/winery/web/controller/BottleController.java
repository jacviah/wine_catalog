package by.jacviah.winery.web.controller;

import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.sevice.BottleService;
import by.jacviah.winery.sevice.MetadataService;
import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.web.rq.CreateBottle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Year;
import java.util.List;

@Controller
@RequestMapping("/bottle")
public class BottleController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final BottleService bottleService;
    private final MetadataService metadataService;
    private final UserService userService;

    public BottleController(BottleService bottleService,
                            MetadataService metadataService,
                            UserService userService) {
        this.bottleService = bottleService;
        this.metadataService = metadataService;
        this.userService = userService;
    }

    @GetMapping
    public String createBottlePage(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        User auth = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUser(auth.getUsername());

        List<Bottle> bottles = bottleService.getUserBottlesByPages(user, PageRequest.of(0, 5));
        rq.setAttribute("bottles", bottles);
        return "bottle";
    }

    @PostMapping
    public String createBottle(@Valid CreateBottle rq, BindingResult result,
                               ModelMap map, UsernamePasswordAuthenticationToken authentication) {
        if(result.hasErrors()) {
            log.info("create bottle errors: ", result.getAllErrors());
            return "bottle";
        }
        Wine wine = Wine.WineBuilder.aWine()
                .withWinery(rq.getWinery())
                .withName(rq.getWine())
                .withCountry(metadataService.getCountry(rq.getCountry()))
                .withRegion(metadataService.getRegion(rq.getRegion()))
                .withGrape(metadataService.getGrape(rq.getGrape()))
                .build();
        User user = userService.findUser(
                SecurityContextHolder.getContext().getAuthentication().getName());
        Bottle bottle = Bottle.BottleBuilder.aBottle()
                .withWine(wine)
                .withUser(user)
                .withYear(Year.of(rq.getYear()))
                .build();
        boolean createResult = bottleService.addBottle(bottle);
        return "redirect:/bottle";
    }
}