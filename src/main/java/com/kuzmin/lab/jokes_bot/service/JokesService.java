package com.kuzmin.lab.jokes_bot.service;

import com.kuzmin.lab.jokes_bot.model.Joke;

import java.time.LocalDate;
import java.util.List;

public interface JokesService {

    public Joke addJokes(Joke joke);

    public List<Joke> getAllJokes(
            LocalDate createdAt,
            int page,
            int size
    );

    public Joke getJokeById(Long id);

    public Joke editJoke(Long id, Joke joke);

    public boolean deleteJoke(Long id);
}
