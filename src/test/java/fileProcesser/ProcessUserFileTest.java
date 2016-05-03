package fileProcesser;

import com.bongani.exception.InvalidFileException;
import com.bongani.fileProcesser.IProcessUserFile;
import com.bongani.fileProcesser.impl.ProcessUserFile;
import com.bongani.model.UserAccount;
import org.junit.Test;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;

public class ProcessUserFileTest {

    @Test(expected = InvalidFileException.class)
    public void should_return_error_message_given_invalid_file_path() throws Exception {
        final String userFilePath = "src/main/resources/dataFiles/invalidUser.txt";

        IProcessUserFile userFile = new ProcessUserFile();
        userFile.processUserAccount(new File(userFilePath));
    }

    @Test
    public void should_process_list_of_users_given_valid_user_file_path() throws Exception {
        final String userFilePath = "src/main/resources/dataFiles/user.txt";
        Set<String> follower = new LinkedHashSet<String>();
        follower.add("Martin");

        UserAccount userAccount = new UserAccount("Alan", follower);

        IProcessUserFile userFile = new ProcessUserFile();
        Set<UserAccount> userAccounts = userFile.processUserAccount(new File(userFilePath));
        assertThat(userAccounts.size(), greaterThan(0));
        assertTrue(userAccounts.contains(userAccount));
    }


}
