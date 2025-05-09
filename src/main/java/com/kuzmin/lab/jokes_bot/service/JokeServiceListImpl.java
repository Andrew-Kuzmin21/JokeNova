package com.kuzmin.lab.jokes_bot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.kuzmin.lab.jokes_bot.model.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Primary
public class JokeServiceListImpl implements JokesService {

    private ArrayList<Joke> jokes = new ArrayList<>();

    @Override
    public Joke addJokes(Joke joke) {
        jokes.add(joke);
        return joke;
    }

    @Override
    public List<Joke> getAllJokes(LocalDate createdAt, int page, int size) {
        return jokes;
//        int from = page * size;
//        int to = Math.min(from + size, jokes.size());
//        if (from >= jokes.size()) return new ArrayList<>();
//        return jokes.subList(from, to);
    }

    @Override
    public Joke getJokeById(Long id) {
        return jokes.get(id.intValue());
    }

    @Override
    public Joke editJoke(Long id, Joke joke) {
        jokes.remove(id.intValue());
        jokes.add(id.intValue(), joke);
        return joke;
    }

    @Override
    public boolean deleteJoke(Long id) {
        jokes.remove(id.intValue());
        return false;
    }
}
