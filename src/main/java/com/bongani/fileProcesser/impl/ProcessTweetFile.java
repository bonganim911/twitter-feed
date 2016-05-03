package com.bongani.fileProcesser.impl;

import com.bongani.fileProcesser.IProcessTweetFile;
import com.bongani.helper.InputStreamReaderHelper;
import com.bongani.model.Tweet;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessTweetFile implements IProcessTweetFile {
    private static final Logger LOG = Logger.getLogger(ProcessTweetFile.class);

    @Override
    public List<Tweet> processTweet(File tweetFile) throws IOException {
        LOG.info("Started the processing of a tweet file...");

        List<Tweet> tweets = new ArrayList<Tweet>();

        LineNumberReader lineNumberReader = new InputStreamReaderHelper().getLineNumberReader(tweetFile);

        String line;
        while ((line = lineNumberReader.readLine()) != null) {
            final String[] values = line.split("> ");
            final String user = values[0];
            final String message = values[1];

            Tweet tweet = new Tweet(user, message);
            tweets.add(tweet);
        }

        lineNumberReader.close();

        LOG.info("Completed the processing of a tweet file...");

        return tweets;
    }


}




