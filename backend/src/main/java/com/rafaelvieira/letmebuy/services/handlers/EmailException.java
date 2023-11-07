package com.rafaelvieira.letmebuy.services.handlers;

import java.io.Serial;

/**
 * @author rafae
 */
public class EmailException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EmailException(String msg) {
        super(msg);
    }
}
