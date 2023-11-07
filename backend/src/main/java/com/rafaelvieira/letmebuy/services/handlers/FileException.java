package com.rafaelvieira.letmebuy.services.handlers;

import java.io.Serial;

/**
 * @author rafae
 */
public class FileException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileException(String msg) {
        super(msg);
    }

    public FileException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
