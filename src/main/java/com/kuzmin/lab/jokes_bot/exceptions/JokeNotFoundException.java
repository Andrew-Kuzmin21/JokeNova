package com.kuzmin.lab.jokes_bot.exceptions;

import lombok.Getter;

@Getter
public class JokeNotFoundException extends RuntimeException {

    private final Long id;

    public JokeNotFoundException(Long id) {
        super("Could not find joke with id " + id);
        this.id = id;
    }
}
