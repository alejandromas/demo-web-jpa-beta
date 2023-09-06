package com.alejandromas.demowebjpabeta.data.repository;

import com.alejandromas.demowebjpabeta.data.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository
        extends PagingAndSortingRepository<Country, Long>, JpaRepository<Country, Long> {
    Page<Country> findCountriesByNameLikeIgnoreCase(final String name, final Pageable pageable);
}
