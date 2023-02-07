package com.rafaelvieira.letmebuy.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rafaelvieira.letmebuy.services.handlers.FileException;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author rafae
 */
@Service
public class S3Service {

    private static Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired(required = false)
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;


    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is, fileName, contentType);
        } catch (IOException e) {
            throw new FileException("Erro de IO: " + e.getMessage());
        }
    }

    public URI uploadFile(InputStream is, String fileName, String contentType) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            LOG.info("Iniciando upload");
            s3client.putObject(bucketName, fileName, is, meta);
            LOG.info("Upload finalizado");
            return s3client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }
    }
}
