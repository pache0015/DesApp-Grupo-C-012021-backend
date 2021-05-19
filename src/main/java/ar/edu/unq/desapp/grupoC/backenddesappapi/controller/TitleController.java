package ar.edu.unq.desapp.grupoC.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications.TitleByGenres;
import ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications.TitleByMaxRating;
import ar.edu.unq.desapp.grupoC.backenddesappapi.controller.specifications.TitleByMinRating;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "title")
public class TitleController {

    @Autowired
    TitleService titleService;

    @GetMapping
    public List<Title> getTitles(@RequestParam(required = false) Double minRating,
                                 @RequestParam(required = false) Double maxRating,
                                 @RequestParam(required = false) List<String> genres,
                                 Pageable pageable) {
        Specification<Title> specs = Specification.where(new TitleByMinRating(minRating))
                .and(new TitleByMaxRating(maxRating))
                .and(new TitleByGenres(genres));

        return titleService.getTitles(specs, pageable);
    }
}
