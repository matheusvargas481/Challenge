package com.matheusvargas481.challenge.dateanalysis.service;

import com.matheusvargas481.challenge.dateanalysis.builder.BuildProcessor;
import com.matheusvargas481.challenge.dateanalysis.domain.Client;
import com.matheusvargas481.challenge.dateanalysis.domain.Sale;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileProcessor {
    private BuildProcessor buildProcessor;

    public void readFile() {
        buildProcessor = new BuildProcessor();
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

    public void writeFile() {
        try {
            File arq = new File("/home/ilegra/data/out/out.dat");
            arq.delete();
            arq.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arq));
            bufferedWriter.write("Total Number of Input Clients: " + buildProcessor.getClientList().size() + "\n");
            bufferedWriter.write("Total number of input sellers: " + buildProcessor.getSaleItemsList().size() + "\n");
            bufferedWriter.write("The most expensive sale: " + buildProcessor + "\n");
            bufferedWriter.write("The worst seller: " + buildProcessor + "\n");
            bufferedWriter.close();
        } catch (IOException erro) {
            System.out.printf("Erro:", erro.getMessage());
        }
    }
}