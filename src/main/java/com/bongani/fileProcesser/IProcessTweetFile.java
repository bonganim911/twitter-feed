package com.bongani.fileProcesser;

import com.bongani.model.Tweet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IProcessTweetFile {
    List<Tweet> processTweet(File tweetFile) throws IOException;
}
