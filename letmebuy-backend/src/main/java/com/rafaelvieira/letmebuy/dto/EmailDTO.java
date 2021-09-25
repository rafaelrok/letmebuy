package com.rafaelvieira.letmebuy.dto;

import java.io.Serializable;

public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fromEmail;
    private String fromName;
    private String replyto;
    private String to;
    private String subject;
    private String body;
    private String contentType;

    public EmailDTO(){

    }

    public EmailDTO(String fromEmail, String fromName, String replyto, String to, String subject, String body, String contentType) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.replyto = replyto;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.contentType = contentType;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getReplyto() {
        return replyto;
    }

    public void setReplyto(String replyto) {
        this.replyto = replyto;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
