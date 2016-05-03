package com.bongani.fileProcesser;

import com.bongani.model.UserAccount;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface IProcessUserFile {
    Set<UserAccount> processUserAccount(File userFile) throws IOException;
}
