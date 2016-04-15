package com.antipov.cf.comparing;

import com.antipov.cf.CodeforcesService;
import com.antipov.cf.ContestCache;
import com.antipov.cf.RatingsHelper;
import com.antipov.cf.dto.Contest;
import com.antipov.cf.dto.RatingChange;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew Antipov
 * Date: 31.03.2016
 * Time: 0:25
 */
@Controller
public class ComparingController {

    private static final Logger logger 	= LoggerFactory.getLogger(ComparingController.class);

	@Autowired
    private CodeforcesService codeforcesService;

    @Autowired
    private RatingsHelper ratingsHelper;

	@Autowired
	private ContestCache contestCache;

	@RequestMapping(value = "/comparing", method = RequestMethod.GET)
	public String comparingForm(Model model) {
        model.asMap().clear();
		model.addAttribute("comparing", new Comparing());
        model.addAttribute("showRes", false);
		return "comparing";
	}

	@RequestMapping(value = "/comparing", method = RequestMethod.POST)
	public String comparingSubmit(@ModelAttribute Comparing comparing, Model model) {
		try {
            if(!comparing.isValid()) {
                model.addAttribute("showRes", false);
                return "comparing";
            }
			compareUsers(comparing, model);
            model.addAttribute("showRes", true);
			return "comparing";
		} catch (RuntimeException e) {
            logger.error("Failed to compare changes", e);
            model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	private void compareUsers(Comparing comparing, Model model) {
		List<RatingChange> firstContests = codeforcesService.getRatingChanges(comparing.getFirstHandle());
		List<RatingChange> secondContests = codeforcesService.getRatingChanges(comparing.getSecondHandle());
		if(firstContests == null) throw new RuntimeException("User with handle " + comparing.getFirstHandle() + " doesn't exist");
		if(secondContests == null) throw new RuntimeException("User with handle " + comparing.getSecondHandle() + " doesn't exist");

		HashMap<Integer, Integer> firstRanks = new HashMap<>();
		firstContests.forEach((contest) -> firstRanks.put(contest.getContestId(), contest.getRank()));
		int total = 0;
		int firstWin = 0;
		int secondWin = 0;

		List<ContestInfo> contestInfos = new ArrayList<>();

		for(RatingChange change: secondContests) {
            if(ratingsHelper.isTeamContest(change)) continue;
			if(!firstRanks.containsKey(change.getContestId())) continue;
			Contest contest = contestCache.getContest(change.getContestId());
			total++;
			int firstRank = firstRanks.get(change.getContestId());
			int secondRank = change.getRank();
			if(firstRank > secondRank) {
				secondWin++;
			} else if(secondRank > firstRank) {
                firstWin++;
            }
            ContestInfo curContestInfo = new ContestInfo(change.getContestId(),
                    change.getContestName(),
                    comparing.getFirstHandle(),
                    firstRank,
                    comparing.getSecondHandle(),
                    secondRank,
                    total,
					new DateTime(contest.getStartTimeSeconds() * 1000));
            contestInfos.add(curContestInfo);
		}
        model.addAttribute("comparing", comparing);
        model.addAttribute("contests", contestInfos);
        model.addAttribute("firstWin", firstWin);
        model.addAttribute("secondWin", secondWin);

		String firstColor;
		if(firstContests.isEmpty()) {
			firstColor = "black";
		} else {
			firstColor = ratingsHelper.getColor(firstContests.get(firstContests.size()-1).getNewRating());
		}
		String secondColor;
		if(secondContests.isEmpty()) {
			secondColor = "black";
		} else {
			secondColor = ratingsHelper.getColor(secondContests.get(secondContests.size()-1).getNewRating());
		}
		model.addAttribute("firstColor", firstColor);
		model.addAttribute("secondColor", secondColor);
	}

}
