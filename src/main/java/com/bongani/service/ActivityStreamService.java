package com.bongani.service;

import com.bongani.fileProcesser.ProcessTweetFile;
import com.bongani.fileProcesser.ProcessUserFile;
import com.bongani.fileProcesser.impl.ProcessTweetFileImpl;
import com.bongani.fileProcesser.impl.ProcessUserFileImpl;
import com.bongani.model.Tweet;
import com.bongani.model.UserAccount;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ActivityStreamService {
    private static final Logger LOG = Logger.getLogger(ActivityStreamService.class);

    private ProcessTweetFile tweetProc;
    private ProcessUserFile usersProc;

    public ActivityStreamService(ProcessTweetFileImpl processTweetFileImpl, ProcessUserFileImpl processUserFileImpl) {
        this.tweetProc = processTweetFileImpl;
        this.usersProc = processUserFileImpl;
    }

    public void displayTweets(File tweetsFile, File userFile) {
        LOG.info("Execution of user activity twitter feed started...");

        Map<String, ArrayList<String>> tweetsMap = generateActivityFeeds(tweetsFile, userFile);

        sortMap(tweetsMap).forEach((name, list) -> {
            System.out.println(name);
            list.forEach(System.out::println);
        });
        LOG.info("Execution of user activity twitter ended....");
    }

    private Map<String, ArrayList<String>> sortMap(Map<String, ArrayList<String>> unsortedTweetMap) {
        Map<String, ArrayList<String>> treeMap = new TreeMap<>(
                new Comparator<String>() {

                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }

                });
        treeMap.putAll(unsortedTweetMap);

        return treeMap;
    }

    public Map<String, ArrayList<String>> generateActivityFeeds(File tweetsFile, File userFile) {
        Map<String, ArrayList<String>> streamActivityMap = new TreeMap<>();
        ArrayList<String> tweetList;

        try {
            List<Tweet> tweets = tweetProc.processTweet(tweetsFile);
            Set<UserAccount> users = usersProc.processUserAccount(userFile);

            for (UserAccount currentUser : users) {
                tweetList = new ArrayList<>();

                for (Tweet currentTweet : tweets) {
                    String owner = currentTweet.getOwner();

                    if (currentUser.getName().equals(owner) || currentUser.getFollowers().contains(owner)) {
                        tweetList.add(String.format("\t@%s: %s", owner, currentTweet.getMessage()));
                    }
                }
                streamActivityMap.put(currentUser.getName(), tweetList);
            }

        } catch (IOException e) {
            LOG.error("*********************************************");
            LOG.error("Unable to execute tweets correctly.");
            LOG.error(e.getMessage());
            LOG.error(e.getStackTrace().toString());
            LOG.error("**********************************************");
        }
        LOG.info("Tweet activity stream is formatter successfully.");
        return streamActivityMap;
    }
}
