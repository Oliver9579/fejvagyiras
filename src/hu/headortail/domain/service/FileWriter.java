package hu.headortail.domain.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter {

    private final String output;

    public FileWriter(String output) {
        this.output = output;
    }

    public void write(List<String> content) {
        try {
            Files.write(Paths.get(output), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
