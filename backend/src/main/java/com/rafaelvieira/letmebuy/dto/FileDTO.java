package com.rafaelvieira.letmebuy.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

public class FileDTO {

    private MultipartFile file;

    public FileDTO(){}

    public FileDTO(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
}
