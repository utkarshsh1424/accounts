package com.learnms.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when customer already exist.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistException extends RuntimeException {

    public CustomerAlreadyExistException(String msj) {
        super(msj);
    }
}

