package com.rafaelvieira.letmebuy.services.handlers;

/**
 * @author rafae
 */
public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String msg) {
		super(msg);
	}
}
