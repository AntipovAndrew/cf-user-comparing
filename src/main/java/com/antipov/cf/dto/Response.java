package com.antipov.cf.dto;

/**
 * @author antipov.
 *         01.04.2016 12:15.
 */
public abstract class Response {
    private String status;
    private String comment;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
