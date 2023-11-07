package com.rafaelvieira.letmebuy.services.handlers;

import java.io.Serial;

/**
 * @author rafae
 */
public class ForbiddenException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public ForbiddenException(String msg) {
		super(msg);
	}
}
