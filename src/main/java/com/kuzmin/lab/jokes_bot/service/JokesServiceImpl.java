package com.kuzmin.lab.jokes_bot.service;

import com.kuzmin.lab.jokes_bot.exceptions.JokeNotFoundException;
import com.kuzmin.lab.jokes_bot.model.Joke;
import com.kuzmin.lab.jokes_bot.repository.JokesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Primary
public class JokesServiceImpl implements JokesService {

    private final JokesRepository jokesRepository;

    @Transactional
    public Joke addJokes(Joke joke) {
        return jokesRepository.save(joke);
    }

    public List<Joke> getAllJokes(
            LocalDate createdAt,
            int page,
            int size
    ) {
//        List<Joke> jokes = new ArrayList<>();
//        return jokes;
//        return (List<Joke>) jokesRepository.findAll(PageRequest.of(page, size));
        if (createdAt == null) {
            List<Joke> jokes = new ArrayList<>();
            jokesRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(Sort.Order.asc("createdAt")))).forEach(jokes::add);
            return jokes;
        }
        return jokesRepository.findAllByCreatedAt(createdAt, PageRequest.of(page, size).withSort(Sort.by(Sort.Order.asc("createdAt"))));
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
