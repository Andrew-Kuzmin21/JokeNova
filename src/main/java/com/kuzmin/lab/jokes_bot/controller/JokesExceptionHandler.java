package com.kuzmin.lab.jokes_bot.controller;

import com.kuzmin.lab.jokes_bot.exceptions.ExceptionResponse;
import com.kuzmin.lab.jokes_bot.exceptions.JokeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class JokesExceptionHandler {

    @ExceptionHandler(JokeNotFoundException.class)
    public ResponseEntity<Void> handleJokesNotFound(JokeNotFoundException exception) {
        log.warn("Jokes not found: {}", exception.getId(), exception);
        return ResponseEntity.notFound().build();
    }

}
