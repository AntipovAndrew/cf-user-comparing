package com.antipov.cf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "cf.comparing")
public class AppConfiguration {

    private String teamContestIds;

    public Set<Integer> getTeamContestIds() {
        Set<Integer> res = new HashSet<>();
        String[] ids = teamContestIds.split(",");
        Arrays.stream(ids).map(Integer::parseInt).forEach(res::add);
        return res;
    }

    public void setTeamContestIds(String teamContestIds) {
        this.teamContestIds = teamContestIds;
    }
}
