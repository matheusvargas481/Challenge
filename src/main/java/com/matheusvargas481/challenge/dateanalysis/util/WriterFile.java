package com.matheusvargas481.challenge.dateanalysis.util;

import com.matheusvargas481.challenge.dateanalysis.service.ChallengeService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterFile {
    private ChallengeService challengeService;

    public WriterFile(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    public void startWriteFile() {
        try {
            File arq = new File("/home/ilegra/data/out/out.dat");
            arq.delete();
            arq.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arq));
            bufferedWriter.write("Total Number of Input Clients: " + challengeService + "\n");
            bufferedWriter.write("Total number of input sellers: " + challengeService + "\n");
            bufferedWriter.write("The most expensive sale: " + challengeService + "\n");
            bufferedWriter.write("The worst seller: " + challengeService + "\n");
            bufferedWriter.close();
        } catch (IOException erro) {
            System.out.printf("Erro:", erro.getMessage());
        }
    }
}
