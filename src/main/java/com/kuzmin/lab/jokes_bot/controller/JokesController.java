package com.kuzmin.lab.jokes_bot.controller;

import com.kuzmin.lab.jokes_bot.model.Joke;
import com.kuzmin.lab.jokes_bot.service.JokesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/jokes")
@RestController
public class JokesController {

//    public JokesController(
//            @Qualifier("jokesServiceImpl") @Autowired JokesService jokesService,
//            @Qualifier("jokeServiceListImpl") @Autowired  JokesService jokesServiceLocal) {
//        this.jokesService = jokesService;
//        this.jokesServiceLocal = jokesServiceLocal;
//    }
//
//    private final JokesService jokesService;
//
//    private final JokesService jokesServiceLocal;

    private final JokesService jokesService;

    // Внедрение только одного JokesService
    public JokesController(@Qualifier("jokesServiceImpl") @Autowired JokesService jokesService) {
        this.jokesService = jokesService;
    }

    @PostMapping
    public ResponseEntity<Joke> addJokes(@RequestBody Joke joke) {
        //jokesServiceLocal .addJokes(joke);
        joke.setCreatedAt(LocalDate.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(jokesService.addJokes(joke));
    }

    @GetMapping
    public ResponseEntity<List<Joke>> getAllJokes(
            @RequestParam(value = "createdAt", required = false) LocalDate createdAt,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size", required = false, defaultValue = "50") Integer size
    ) {
        return ResponseEntity.ok(jokesService.getAllJokes(createdAt, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joke> getJokeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(jokesService.getJokeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Joke> editJoke(
            @PathVariable("id") Long id,
            @RequestBody Joke joke) {
        return ResponseEntity.ok(jokesService.editJoke(id, joke));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoke(@PathVariable("id") Long id) {
        jokesService.deleteJoke(id);
        return ResponseEntity.ok().build();
    }
}