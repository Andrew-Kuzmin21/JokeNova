package com.kuzmin.lab.jokes_bot.repository;

import com.kuzmin.lab.jokes_bot.model.Joke;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokesRepository extends CrudRepository<Joke, Long> {
}
