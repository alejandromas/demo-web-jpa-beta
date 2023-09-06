package com.alejandromas.demowebjpabeta.rest;

import com.alejandromas.demowebjpabeta.data.model.Country;
import com.alejandromas.demowebjpabeta.service.CountryService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.alejandromas.demowebjpabeta.rest.Utils.API_VERSION;
import static com.alejandromas.demowebjpabeta.rest.Utils.BASE_PATH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = BASE_PATH + API_VERSION + CountryController.COUNTRIES_PATH)
public class CountryController {

    static final String COUNTRIES_PATH = "${application.countries-path}";

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<Page<Country>> findByName(@RequestParam(required = false) final String name, final Pageable pageable) {
        final Page<Country> page;
        if (StringUtils.isNotBlank(name)) {
            page = this.countryService.findByName(name, pageable);
        } else {
            page = this.countryService.findAll(pageable);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable final Long id) {
        return this.countryService.findById(id)
                .map(country -> new ResponseEntity<>(country, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/", method = {POST, PUT})
    public ResponseEntity<Country> insertOrUpdate(@RequestBody final Country country) {
        final var countrySaved = this.countryService.save(country);
        return new ResponseEntity<>(countrySaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        this.countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
