package by.jacviah.winery.web.controller;

import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.sevice.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/meta")
public class MetaController {

    private MetadataService metadataService;

    public MetaController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    private static final String MAIN = "countries/main";

    @GetMapping(value = "/page")
    public String mainPage(ModelMap model) {
        fillModel(model);
        return MAIN;
    }

    private void fillModel(ModelMap model) {
        List<Country> list = metadataService.getCountries();
        model.put("countries", list);
    }

    @GetMapping(value = "/getCounties")
    public String getCountries(HttpServletRequest rq) {
        List<Country> countries = metadataService.getCountries();
        rq.setAttribute("countries", countries);
        return "meta";
    }

    @GetMapping(value = "/getGrapes")
    public List<Grape> getGrapes() {
        return metadataService.getGrapes();
    }

    @GetMapping(value = "/getRegionss")
    public List<Region> getRegions(@RequestParam(value = "country") Country country) {
        return metadataService.getCountryRegions(country);
    }
}
