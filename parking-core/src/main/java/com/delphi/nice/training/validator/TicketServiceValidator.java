package com.delphi.nice.training.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TicketServiceValidator implements Validator {
    File file;
    private String filePath;

    @Override
    public boolean validate(String filePath) {
        file = new File(filePath);
        this.filePath = filePath;
        if (!file.exists()) {
            createByDefault();
            return false;
        }
        return true;
    }

    private void createByDefault() {
        try (FileOutputStream outputStream = new FileOutputStream(filePath);) {
            outputStream.write("[]".getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
