package by.jacviah.winery.web.controller;

import by.jacviah.winery.model.Country;
import by.jacviah.winery.model.Grape;
import by.jacviah.winery.model.Region;
import by.jacviah.winery.sevice.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/wine")
public class MetaController {

    @Autowired
    private MetadataService metadataService;

    @GetMapping
    public List<Country> getCountries(ModelAndView modelAndView) {
        return metadataService.getCountries();
    }

    @GetMapping
    public List<Grape> getGrapes() {
        return metadataService.getGrapes();
    }

    @GetMapping
    public List<Region> getRegions(@RequestParam(value = "country") Country country) {
        return metadataService.getCountryRegions(country);
    }
}
