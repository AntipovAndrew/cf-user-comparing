package com.antipov.cf.comparing;

import com.antipov.cf.comparing.dto.RatingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@Autowired
	CodeforcesService codeforcesService;

	@RequestMapping(value = "/comparing", method = RequestMethod.GET)
	public String comparingForm(Model model) {
		model.addAttribute("comparing", new Comparing());
		return "comparing";
	}

	@RequestMapping(value = "/comparing", method = RequestMethod.POST)
	public String comparingSubmit(@ModelAttribute Comparing comparing, Model model) {
		try {
			compareUsers(comparing);
			model.addAttribute("comparing", comparing);
			return "result";
		} catch (RuntimeException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	private void compareUsers(Comparing comparing) {
		List<RatingChange> firstContests = codeforcesService.getRatingChanges(comparing.getFirstHandle());
		List<RatingChange> secondContests = codeforcesService.getRatingChanges(comparing.getSecondHandle());
		if(firstContests == null) throw new RuntimeException("User with handle " + comparing.getFirstHandle() + " doesn't exist");
		if(secondContests == null) throw new RuntimeException("User with handle " + comparing.getSecondHandle() + " doesn't exist");

		HashMap<Integer, Integer> firstRanks = new HashMap<>();
		firstContests.forEach((contest) -> firstRanks.put(contest.getContestId(), contest.getRank()));
		int total = 0;
		int firstWin = 0;
		int secondWin = 0;
		for(RatingChange change: secondContests) {
			if(!firstRanks.containsKey(change.getContestId())) continue;
			total++;
			int firstRank = firstRanks.get(change.getContestId());
			int secondRank = change.getRank();
			if(firstRank > secondRank) {
				secondWin++;
			} else if(secondRank > firstRank) {
				firstWin++;
			}
		}
		comparing.setBoth(total);
		comparing.setFirstWin(firstWin);
		comparing.setSecondWin(secondWin);
	}

}
