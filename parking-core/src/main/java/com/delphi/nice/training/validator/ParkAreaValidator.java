package com.delphi.nice.training.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ParkAreaValidator implements Validator {
    File checkFile;
    String filePath;

    @Override
    public boolean validate(String filePath) {
        this.filePath = filePath;
        checkFile = new File(filePath);
        if (!checkFile.exists()) {
            createFileByDefault();
            return false;
        }
        return true;
    }

    private void createFileByDefault() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write("[{parkingSlot:1,isParked: false}]".getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
