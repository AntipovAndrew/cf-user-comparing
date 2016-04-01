package com.antipov.cf;

import com.antipov.cf.dto.RatingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RatingsHelper {

    @Autowired
    private AppConfiguration configuration;

    private Set<Integer> teamContests;

    public boolean isTeamContest(RatingChange ratingChange) {
        if(teamContests == null) {
            teamContests = configuration.getTeamContestIds();
        }
        return teamContests.contains(ratingChange.getContestId());
    }

    public String getColor(int rating) {
        if(rating >= 2400) return "red";
        if(rating >= 2200) return "#FF8C00";
        if(rating >= 1900) return "#a0a";
        if(rating >= 1600) return "blue";
        if(rating >= 1400) return "#03A89E";
        if(rating >= 1200) return "green";
        return "gray";

    }
}
