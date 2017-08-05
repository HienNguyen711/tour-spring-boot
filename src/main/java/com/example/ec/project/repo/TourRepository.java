package com.example.ec.project.repo;


import com.example.ec.project.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.awt.print.Pageable;
import java.util.List;


/**
 * TOUR REPOSITORY
 */

public interface TourRepository extends PagingAndSortingRepository<Tour, Integer> {

    /**
     * LOOK UP A PAGE OF TOURS ASSOCIATED WITH A TOURPACKAGE
     * @param code
     * @return
     */
//get paging and sorting

    Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);


    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends Tour> S save(S s);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends Tour> Iterable<S> save(Iterable<S> iterable);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void delete(Integer integer);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void delete(Tour tour);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Tour> iterable);

    //Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    void deleteAll();





}
