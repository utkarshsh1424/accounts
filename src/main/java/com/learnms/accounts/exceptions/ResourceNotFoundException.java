package com.learnms.accounts.exceptions;

import com.learnms.accounts.constants.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when resource for the provided identifier not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format(ErrorConstants.ERR_RESOURCE_NOT_FOUND, resourceName, fieldName, fieldValue));
    }
}
