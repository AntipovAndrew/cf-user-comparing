package com.antipov.cf;

import com.antipov.cf.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew Antipov
 * Date: 31.03.2016
 * Time: 0:03
 */
@Service
public class CodeforcesService {

	private static final Logger logger 	= LoggerFactory.getLogger(CodeforcesService.class);
	private static final String userRatingUrl = "http://codeforces.com/api/user.rating";
	private static final String contestListUrl = "http://codeforces.com/api/contest.list";


	private RestTemplate restTemplate;

	public CodeforcesService() {
		restTemplate = new RestTemplate();
	}

	public List<RatingChange> getRatingChanges(String handle) {
		try {
			String uriString = UriComponentsBuilder.fromHttpUrl(userRatingUrl).
					queryParam("handle", handle).toUriString();

			UserRatingResponse res = restTemplate.getForObject(uriString, UserRatingResponse.class);
			if (!res.getStatus().equals("OK")) {
				logger.warn("Failed to get changes for user with handle \"{}\"\nComment: {}", handle, res.getComment());
				return null;
			}
			return res.getResult();
		} catch (Exception e) {
			logger.error("Failed to load changes", e);
			return null;
		}
	}

	public List<Contest> getAllContests(boolean gyms) {
		try {
			String uriString = UriComponentsBuilder.fromHttpUrl(contestListUrl).
					queryParam("gym", gyms).toUriString();
			ContestListResponse response = restTemplate.getForObject(uriString, ContestListResponse.class);
			if(!response.getStatus().equals("OK")) {
				logger.warn("Failed to load contest. Comment: {}", response.getComment());
				return null;
			}
			return response.getResult();
		} catch (Exception e) {
			logger.error("Failed to load contests", e);
			return null;
		}
	}
}
