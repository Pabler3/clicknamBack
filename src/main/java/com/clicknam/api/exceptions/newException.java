package com.clicknam.api.exceptions;

import org.apache.coyote.BadRequestException;

public class newException extends BadRequestException {

    public newException(String message) {
        super(message);
    }

}
