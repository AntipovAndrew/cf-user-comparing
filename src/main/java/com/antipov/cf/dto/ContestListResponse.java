package com.antipov.cf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author antipov.
 *         01.04.2016 12:15.
 */
@JsonIgnoreProperties
public class ContestListResponse extends Response {
    private List<Contest> result;

    public List<Contest> getResult() {
        return result;
    }

    public void setResult(List<Contest> result) {
        this.result = result;
    }
}
