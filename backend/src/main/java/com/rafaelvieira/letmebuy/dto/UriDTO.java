package com.rafaelvieira.letmebuy.dto;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class UriDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String uri;

    public UriDTO(){
    }

    public UriDTO(String uri) {
        this.uri = uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
