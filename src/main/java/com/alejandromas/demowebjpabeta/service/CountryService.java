package com.alejandromas.demowebjpabeta.service;

import com.alejandromas.demowebjpabeta.data.model.Country;
import com.alejandromas.demowebjpabeta.data.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    @Cacheable(cacheNames = "countries-by-id")
    public Optional<Country> findById(final Long id) {
        return countryRepository.findById(id);
    }

    @Cacheable(cacheNames = "countries-by-name")
    public Page<Country> findByName(final String name, final Pageable pageable) {
        return this.countryRepository.findCountriesByNameLikeIgnoreCase(name + "%", pageable);
    }

    @Cacheable(cacheNames = "countries-all")
    public Page<Country> findAll(final Pageable pageable) {
        return this.countryRepository.findAll(pageable);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = {"countries-by-name", "countries-all"}, allEntries = true),
            @CacheEvict(cacheNames = "countries-by-id")})
    public void delete(final Long id) {
        this.countryRepository.deleteById(id);
    }

    @Transactional
    @Caching(
            evict = @CacheEvict(cacheNames = {"countries-by-name", "countries-all"}, allEntries = true),
            put = @CachePut(key = "#country.id", cacheNames = "countries-by-id"))
    public Country save(final Country country) {
        return this.countryRepository.save(country);
    }
}
