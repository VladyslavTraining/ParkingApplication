package com.delphi.nice.training.validator;


import lombok.SneakyThrows;

import java.io.File;
import java.io.FileNotFoundException;

public class ParkAreaValidator implements Validator {
    File checkFile;
    String filePath;

    @SneakyThrows
    @Override
    public boolean validate(String filePath) {
        this.filePath = filePath;
        checkFile = new File(filePath);
        if (!checkFile.exists()) {
                throw new FileNotFoundException();
        }
        return true;
    }

}
