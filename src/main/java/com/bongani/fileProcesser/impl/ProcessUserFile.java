package com.bongani.fileProcesser.impl;

import com.bongani.exception.InvalidSyntaxException;
import com.bongani.fileProcesser.IProcessUserFile;
import com.bongani.helper.InputStreamReaderHelper;
import com.bongani.model.UserAccount;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

public class ProcessUserFile implements IProcessUserFile {
    private static final String FOLLOWS = "follows";
    private static final Logger LOG = Logger.getLogger(ProcessUserFile.class);

    @Override
    public Set<UserAccount> processUserAccount(File userFile) throws IOException {
        LOG.info("Started the processing of a user file");

        LineNumberReader lineNumberReader;

        Map<String, UserAccount> userAccountHashMap = new HashMap<>();


        lineNumberReader = new InputStreamReaderHelper().getLineNumberReader(userFile);

        String line;
        while ((line = lineNumberReader.readLine()) != null) {
            int followsKeyIndex = line.indexOf(FOLLOWS);

            if (followsKeyIndex < 0) {
                throw new InvalidSyntaxException(String.format("Error found on user file at line: %d ", lineNumberReader.getLineNumber()));
            }

            String user = line.substring(0, followsKeyIndex).trim();

            if (line.contains(FOLLOWS)) {
                String followerCheck = line.substring(followsKeyIndex + FOLLOWS.length()).trim();

                if (followerCheck.isEmpty()) {
                    throw new InvalidSyntaxException(String.format("Error found on user file at line: %d ", lineNumberReader.getLineNumber()));
                }
            }

            String[] rawFollowers = line.substring(followsKeyIndex + FOLLOWS.length()).split(",");

            UserAccount userAccount = getUserAccount(userAccountHashMap, user);

            assignFollowerUserAccount(userAccountHashMap, rawFollowers, userAccount);
        }
        lineNumberReader.close();


        Set<UserAccount> accounts = new TreeSet<>();
        accounts.addAll(userAccountHashMap.values());

        LOG.info("Completed the processing of a users");

        return accounts;
    }

    private void assignFollowerUserAccount(Map<String, UserAccount> userAccountHashMap, String[] rawFollowers, UserAccount userAccount) {
        Set<String> followers = userAccount.getFollowers();
        for (String currentFollower : rawFollowers) {
            String follower = currentFollower.trim();
            followers.add(follower);

            UserAccount followerAccount;
            if (!userAccountHashMap.containsKey(follower)) {
                followerAccount = new UserAccount();
                followerAccount.setName(follower);
                followerAccount.setFollowers(new HashSet<>());

                userAccountHashMap.put(follower, followerAccount);
            }
        }
    }

    private UserAccount getUserAccount(Map<String, UserAccount> userAccountHashMap, String user) {
        UserAccount userAccount;
        if (!userAccountHashMap.containsKey(user)) {
            userAccount = new UserAccount();
            userAccount.setName(user);
            userAccount.setFollowers(new HashSet<>());

            userAccountHashMap.put(user, userAccount);
        } else {
            userAccount = userAccountHashMap.get(user);
        }
        return userAccount;
    }

}
