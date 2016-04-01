package com.antipov.cf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew Antipov
 * Date: 30.03.2016
 * Time: 23:53
 */
@JsonIgnoreProperties
public class RatingChange {
	private int contestId;

	private String contestName;

	private int rank;

	private String handle;

	private int newRating;

	public int getContestId() {
		return contestId;
	}

	public void setContestId(int contestId) {
		this.contestId = contestId;
	}

	public String getContestName() {
		return contestName;
	}

	public void setContestName(String contestName) {
		this.contestName = contestName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public int getNewRating() {
		return newRating;
	}

	public void setNewRating(int newRating) {
		this.newRating = newRating;
	}
}
