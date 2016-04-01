package com.antipov.cf;

import com.antipov.cf.dto.Contest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author antipov.
 *         01.04.2016 12:20.
 */
@Component
public class ContestCache {

    private static final Logger logger = LoggerFactory.getLogger(ContestCache.class);

    private HashMap<Integer, Contest> id2Contest = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Autowired
    private CodeforcesService codeforcesService;

    @Scheduled(fixedDelay = 1000 * 60 * 30)
    private void update() {
        lock.writeLock().lock();
        try {
            List<Contest> contests = codeforcesService.getAllContests(false);
            HashMap<Integer, Contest> next = new HashMap<>();
            contests.forEach(contest -> next.put(contest.getId(), contest));
            id2Contest = next;
        } catch (Exception e) {
            logger.error("Failed to load contests", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Contest getContest(int id) {
        lock.readLock().lock();
        try {
            return id2Contest.get(id);
        } finally {
            lock.readLock().unlock();
        }
    }
}
