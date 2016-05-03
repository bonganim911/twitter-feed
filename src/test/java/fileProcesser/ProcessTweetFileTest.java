package fileProcesser;

import com.bongani.exception.InvalidFileException;
import com.bongani.fileProcesser.IProcessTweetFile;
import com.bongani.fileProcesser.impl.ProcessTweetFile;
import com.bongani.model.Tweet;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class ProcessTweetFileTest {

    @Test
    public void should_process_list_of_tweets_given_valid_tweet_file() throws Exception {
        final String tweetFilePath = "src/main/resources/dataFiles/tweet.txt";
        Tweet expectedTweet = new Tweet("Alan", "If you have a procedure with 10 parameters, you probably missed some.");

        IProcessTweetFile tweetsFile = new ProcessTweetFile();
        List<Tweet> tweets = tweetsFile.processTweet(new File(tweetFilePath));
        assertThat(tweets.size(), greaterThan(0));
        assertThat(tweets.get(0).getOwner(), is(expectedTweet.getOwner()));
    }

    @Test(expected = InvalidFileException.class)
    public void should_return_error_message_given_invalid_file_path() throws Exception {
        final String tweetFilePath = "src/main/resources/dataFiles/tweetss.txt";

        IProcessTweetFile tweetsFile = new ProcessTweetFile();
        tweetsFile.processTweet(new File(tweetFilePath));
    }
}
