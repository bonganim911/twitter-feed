package com.bongani.helper;

import com.bongani.exception.InvalidFileException;

import java.io.*;
import java.nio.charset.Charset;

public class InputStreamReaderHelper {

    public LineNumberReader getLineNumberReader(File file) throws FileNotFoundException {

        try {
            InputStreamReader inputStreamReader = getInputStreamReader(file);
            return new LineNumberReader(inputStreamReader);

        }catch (FileNotFoundException e){
            throw new InvalidFileException(String.format("Unable to find file provided: %s", file));
        }

    }

    private InputStreamReader getInputStreamReader(File file) throws FileNotFoundException {
        String FILE_ENCODING_TYPE = "US-ASCII";
        return new InputStreamReader(
                        new FileInputStream(file), Charset.forName(FILE_ENCODING_TYPE));
    }
}
