package com.bongani.service;

import com.bongani.fileProcesser.IProcessTweetFile;
import com.bongani.fileProcesser.IProcessUserFile;
import com.bongani.fileProcesser.impl.ProcessTweetFile;
import com.bongani.fileProcesser.impl.ProcessUserFile;
import com.bongani.model.Tweet;
import com.bongani.model.UserAccount;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ActivityStreamService {
//    private static final Logger LOG = LoggerFactory.getLogger(ActivityStreamService.class);

    private IProcessTweetFile tweetProc;
    private IProcessUserFile usersProc;

    public ActivityStreamService(ProcessTweetFile processTweetFile, ProcessUserFile processUserFile) {
        this.tweetProc = processTweetFile;
        this.usersProc = processUserFile;
    }

    public void displayTweets(File tweetsFile, File userFile) {
        Map<String, ArrayList<String>> tweetsMap = formatActivityStream(tweetsFile, userFile);

        sortMap(tweetsMap).forEach((name,list)->{
            System.out.println(name);
            list.forEach(System.out::println);
        });
    }

    private Map<String, ArrayList<String>> sortMap(Map<String, ArrayList<String>> unsortedTweetMap){
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

    public Map<String, ArrayList<String>> formatActivityStream(File tweetsFile, File userFile) {
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

        } catch (IOException io) {
//            LOG.error("Unable to process file correctly.");
        }

        return streamActivityMap;
    }
}
