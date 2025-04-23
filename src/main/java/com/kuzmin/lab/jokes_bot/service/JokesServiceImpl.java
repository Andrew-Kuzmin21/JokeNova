package com.kuzmin.lab.jokes_bot.service;

import com.kuzmin.lab.jokes_bot.exceptions.JokeNotFoundException;
import com.kuzmin.lab.jokes_bot.model.Joke;
import com.kuzmin.lab.jokes_bot.repository.JokesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Primary
public class JokesServiceImpl implements JokesService {

    private final JokesRepository jokesRepository;

    public Joke addJokes(Joke joke) {
        return jokesRepository.save(joke);
    }

    public List<Joke> getAllJokes() {
//        List<Joke> jokes = new ArrayList<>();
//        return jokes;
        return (List<Joke>) jokesRepository.findAll();
    }

    public Joke getJokeById(Long id) {
        Optional<Joke> joke = jokesRepository.findById(id);
        if (joke.isPresent()) {
            return joke.get();
        }
        else {
            throw new JokeNotFoundException(id);
        }
    }

    public Joke editJoke(
            Long id,
            Joke joke) {
        Optional<Joke>jokeFind = jokesRepository.findById(id);
        if (jokeFind.isPresent()) {
            joke.setId(id);
            return jokesRepository.save(joke);
        }
        else {
            throw new JokeNotFoundException(id);
        }
    }

    public boolean deleteJoke(Long id) {
        jokesRepository.deleteById(id);
        return false;
    }

}
