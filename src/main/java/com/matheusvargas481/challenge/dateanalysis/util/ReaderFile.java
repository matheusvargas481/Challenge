package com.matheusvargas481.challenge.dateanalysis.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {
    private String path;
    private String extension;

    public ReaderFile() {
        this.path = "/home/ilegra/data/in";
        this.extension = "dat";
    }

    public List<String> read() {
        List<String> lines = new ArrayList<>();
        try {
            Files.list(Paths.get(this.path))
                    .filter(path -> path.toString().endsWith("." + extension))
                    .forEach(path -> {
                        try {
                           lines.addAll(Files.readAllLines(path));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}