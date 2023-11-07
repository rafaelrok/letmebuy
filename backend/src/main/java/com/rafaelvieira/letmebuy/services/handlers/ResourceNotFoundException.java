package com.rafaelvieira.letmebuy.services.handlers;

import java.io.Serial;

/**
 * @author rafae
 */
public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
