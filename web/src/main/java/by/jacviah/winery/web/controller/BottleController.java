package by.jacviah.winery.web.controller;

import by.jacviah.winery.model.Bottle;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.Wine;
import by.jacviah.winery.sevice.BottleService;
import by.jacviah.winery.sevice.MetadataService;
import by.jacviah.winery.sevice.UserService;
import by.jacviah.winery.web.rq.BottleForm;
import by.jacviah.winery.web.rq.CreateBottle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

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
        return "bottle";
    }

    @PostMapping("/bottle")
    public String bottle(@Validated CreateBottle rq, BindingResult result,
                         ModelMap map, UsernamePasswordAuthenticationToken authentication) {
        if (result.hasErrors()) {
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

    @GetMapping("/bottle-list")
    public String listBottles(UsernamePasswordAuthenticationToken authentication, ModelMap map) {
        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUser(auth.getUsername());

        List<Bottle> bottles = bottleService.getUserBottlesByPages(user, PageRequest.of(0, 10));
        List<BottleForm> result = bottles.stream().map(bottle -> new BottleForm(bottle))
                .collect(Collectors.toList());
        map.addAttribute("bottles", result);
        return "bottle-list";
    }

    @DeleteMapping("/bottle-list/{id}")
    public String deleteBottle(@PathVariable long id, UsernamePasswordAuthenticationToken authentication,
                             ModelMap map) {
        bottleService.deleteBottle(id);
        return "redirect:/bottle-list";
    }
}