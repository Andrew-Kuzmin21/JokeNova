package com.kuzmin.lab.jokes_bot.repository;

import com.kuzmin.lab.jokes_bot.model.Joke;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JokesRepository extends PagingAndSortingRepository<Joke, Long>, CrudRepository<Joke, Long> {

    List<Joke> findAllByCreatedAt(LocalDate createdAt);
    List<Joke> findAllByCreatedAt(LocalDate createdAt, Pageable pageable);
}
