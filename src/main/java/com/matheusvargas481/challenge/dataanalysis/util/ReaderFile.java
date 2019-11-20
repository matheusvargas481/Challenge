package com.matheusvargas481.challenge.dataanalysis.util;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
public class ReaderFile {
    private final String HOME_DIR = System.getProperty("user.home");
    private String pathIn = HOME_DIR + "/data/in";
    private String pathOut = HOME_DIR + "/data/out";
    private String extension = "dat";

    public List<String> read() {
        List<String> lines = new ArrayList<>();
        Path dirPathIn = Paths.get(pathIn);
        try {
            verifyPath(dirPathIn);
            Files.list(Paths.get(this.pathIn))
                    .filter(path -> path.toString().endsWith("." + extension)).forEach(path -> {
                try {
                    lines.addAll(Files.readAllLines(path));
                } catch (IOException | NoSuchElementException e) {
                    throw new RuntimeException("File empty !");
                }
            });
        } catch (IOException | NoSuchElementException e) {
            throw new RuntimeException("Path not found !");
        }
        Path dirPathOut = Paths.get(pathOut);
        verifyPath(dirPathOut);
        return lines;
    }

    private void verifyPath(Path dirPath) {
        boolean dirExists = Files.exists(dirPath);
        if (dirExists) {
            System.out.println("Directory Already Exists !");
        } else {
            try {
                Files.createDirectories(dirPath);
                System.out.println("New Directory Successfully Created !");
            } catch (IOException | NoSuchElementException ioExceptionObj) {
                throw new RuntimeException("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
    }
}