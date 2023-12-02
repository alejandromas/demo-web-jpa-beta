package com.alejandromas.demowebjpabeta;

import com.alejandromas.demowebjpabeta.data.model.City;
import com.alejandromas.demowebjpabeta.data.model.Country;
import com.alejandromas.demowebjpabeta.data.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@EnableCaching
public class DemoWebJpaBetaApplication implements CommandLineRunner {

    private final CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoWebJpaBetaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        this.populateDatabase();
    }

    private void populateDatabase() {
        //comentarios
        final Country colombia = new Country();
        colombia.setName("Colombia");
        colombia.setPopulation(BigDecimal.valueOf(1000000));

        final Country brazil = new Country();
        brazil.setName("Brazil");
        brazil.setPopulation(BigDecimal.valueOf(3000000));

        final Country england = new Country();
        england.setName("England");
        england.setPopulation(BigDecimal.valueOf(4000000));
        final City london = new City();
        london.setName("London");
        london.setCapital(true);

        final City liverpool = new City();
        liverpool.setName("Liverpool");
        liverpool.setCapital(false);

        england.addCity(london);
        england.addCity(liverpool);

        final Country usa = new Country();
        usa.setName("USA");
        usa.setPopulation(BigDecimal.valueOf(24000000));
        final City washington = new City();
        washington.setName("Washington");
        washington.setCapital(true);
        final City newYork = new City();
        newYork.setName("New York");
        newYork.setCapital(false);
        usa.addCity(washington);
        usa.addCity(newYork);

        List<Country> countries = new ArrayList<>();
        countries.add(colombia);
        countries.add(brazil);
        countries.add(england);
        countries.add(usa);

        this.countryRepository.saveAll(countries);
    }
}
