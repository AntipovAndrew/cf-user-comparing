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
}
