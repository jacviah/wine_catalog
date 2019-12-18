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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.Year;

@Controller
@RequestMapping
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

    @GetMapping("/bottle")
    public String createBottlePage() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "login";
        }
        return "redirect:/bottle";
    }

    @PostMapping("/bottle")
    public String createBottle(@Valid CreateBottle rq, BindingResult result, ModelMap map) {
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