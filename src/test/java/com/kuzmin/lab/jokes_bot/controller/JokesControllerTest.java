package com.kuzmin.lab.jokes_bot.controller;

import com.kuzmin.lab.jokes_bot.model.Joke;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class JokesControllerTest {

    private JokesController jokesController;

    void test() {

        ResponseEntity<Joke> expected = ResponseEntity.ok(new Joke()
                .setId(1L));
        ResponseEntity<Joke> actual = jokesController.addJokes(new Joke().setText("Text"));

    }

}