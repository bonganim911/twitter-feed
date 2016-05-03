package com.bongani;

import com.bongani.fileProcesser.impl.ProcessTweetFile;
import com.bongani.fileProcesser.impl.ProcessUserFile;
import com.bongani.service.ActivityStreamService;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 0){
            ActivityStreamService activityStreamService = new ActivityStreamService(new ProcessTweetFile(), new ProcessUserFile());
            activityStreamService.displayTweets(new File(args[0]), new File(args[1]));
//            activityStreamService.displayTweets(new File("src/main/resources/dataFiles/tweet.txt"), new File("src/main/resources/dataFiles/user.txt"));

        }
    }

}
