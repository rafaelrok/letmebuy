package com.rafaelvieira.letmebuy.services.handlers;

import java.io.Serial;

/**
 * @author rafae
 */
public class DataBaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DataBaseException(String msg) {
        super(msg);
    }
}
