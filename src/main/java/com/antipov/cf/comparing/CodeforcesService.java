package com.antipov.cf.comparing;

import com.antipov.cf.comparing.dto.RatingChange;
import com.antipov.cf.comparing.dto.UserRatingResponse;
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
	private static final String apiUrl = "http://codeforces.com/api/user.rating";

	private RestTemplate restTemplate;

	public CodeforcesService() {
		restTemplate = new RestTemplate();
	}

	public List<RatingChange> getRatingChanges(String handle) {
		try {
			String uriString = UriComponentsBuilder.fromHttpUrl(apiUrl).
					queryParam("handle", handle).toUriString();

			UserRatingResponse res = restTemplate.getForObject(uriString, UserRatingResponse.class);
			if (res.getStatus().equals("OK")) {
				return res.getResult();
			} else {
				logger.warn("Failed to get changes for user with handle \"{}\"\nComment: {}", handle, res.getComment());
				return null;
			}
		} catch (Exception e) {
			logger.error("Failed to load changes", e);
			return null;
		}
	}
}
