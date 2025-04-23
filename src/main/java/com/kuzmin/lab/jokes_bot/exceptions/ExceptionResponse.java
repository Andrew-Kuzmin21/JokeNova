package com.kuzmin.lab.jokes_bot.exceptions;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExceptionResponse {

    private Long code;
    private String message;

}
