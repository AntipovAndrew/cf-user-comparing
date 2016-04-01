package com.antipov.cf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew Antipov
 * Date: 31.03.2016
 * Time: 0:00
 */
@JsonIgnoreProperties
public class UserRatingResponse extends Response{
	private List<RatingChange> result;

	public List<RatingChange> getResult() {
		return result;
	}

	public void setResult(List<RatingChange> result) {
		this.result = result;
	}
}
