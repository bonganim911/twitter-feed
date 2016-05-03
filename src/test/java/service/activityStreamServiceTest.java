package service;

import com.bongani.fileProcesser.impl.ProcessTweetFileImpl;
import com.bongani.fileProcesser.impl.ProcessUserFileImpl;
import com.bongani.service.ActivityStreamService;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class activityStreamServiceTest {
    
    @Test
    public void should_return_format_tweet_activity_given_files() throws Exception {
        ActivityStreamService activityStreamService = new ActivityStreamService(new ProcessTweetFileImpl(), new ProcessUserFileImpl());
        Map<String, ArrayList<String>> expected = activityStreamService.generateActivityFeeds(new File("src/main/resources/dataFiles/tweet.txt"), new File("src/main/resources/dataFiles/user.txt"));
        assertThat(expected.isEmpty(), is(false));
    }
    
}
