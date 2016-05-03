package helper;

import com.bongani.exception.InvalidFileException;
import com.bongani.helper.InputStreamReaderHelper;
import org.junit.Test;

import java.io.File;
import java.io.LineNumberReader;

import static org.junit.Assert.assertNotNull;

public class InputStreamReaderHelperTest {

    @Test
    public void should_return_line_number_reader_given_valid_file() throws Exception {
        InputStreamReaderHelper inputStreamReaderHelper = new InputStreamReaderHelper();
        LineNumberReader lineNumberReader = inputStreamReaderHelper.getLineNumberReader(new File("src/main/resources/dataFiles/user.txt"));
        assertNotNull(lineNumberReader.readLine());
    }


    @Test(expected = InvalidFileException.class)
    public void should_return_exception_given_invalid_file() throws Exception {
        InputStreamReaderHelper inputStreamReaderHelper = new InputStreamReaderHelper();
        inputStreamReaderHelper.getLineNumberReader(new File("src/main/resources/dataFiles/users.txt"));
    }
}
