package com.matheusvargas481.challenge.dateanalysis.service;

import com.matheusvargas481.challenge.dateanalysis.builder.BuildProcessor;
import com.matheusvargas481.challenge.dateanalysis.domain.Client;
import com.matheusvargas481.challenge.dateanalysis.domain.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileProcessor {

    @Autowired
    private BuildProcessor buildProcessor;

    public void readFile() {
        List<String> dados = new ArrayList<>();
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(".dat");
            }
        };
        File dir = new File("/home/ilegra/data/in");
        File[] files = dir.listFiles(filter);
        try {
            for (int i = 0; i < files.length; i++) {
                dados.addAll(Files.lines(Paths.get(files[i].getPath())).collect(Collectors.toList()));
                dados.stream().forEach(separtorString -> {
                    if (separtorString.startsWith(Client.TYPE)) {
                        buildProcessor.client(separtorString);
                    } else if (separtorString.startsWith(Sale.TYPE)) {
                        buildProcessor.sale(separtorString);
                    } else {
                        buildProcessor.salesman(separtorString);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}