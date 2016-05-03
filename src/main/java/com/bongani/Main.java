package com.bongani;

import com.bongani.fileProcesser.impl.ProcessTweetFile;
import com.bongani.fileProcesser.impl.ProcessUserFile;
import com.bongani.service.ActivityStreamService;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            ActivityStreamService activityStreamService = new ActivityStreamService(new ProcessTweetFile(), new ProcessUserFile());
            activityStreamService.displayTweets(new File(args[0]), new File(args[1]));
        } else {
            LOG.info("******************************");
            LOG.info("No file proper files provided");
            LOG.info("******************************");
        }
    }

}
