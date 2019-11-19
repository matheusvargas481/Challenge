package com.matheusvargas481.challenge.dateanalysis.util;

import com.matheusvargas481.challenge.dateanalysis.service.ChallengeService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

public class WriterFile {
    private ChallengeService challengeService;
    private ReaderFile readerFile = new ReaderFile();
    private String homeDir = System.getProperty("user.home");

    public WriterFile(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    public void writerFile() {
        try {

            File arq = new File(homeDir + "/data/out/out." + readerFile.getExtension());
            if (arq.exists()) {
                arq.delete();
                arq.createNewFile();
            }
            arq.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arq));
            bufferedWriter.write("Total Number of Input Clients: " + challengeService.getAmountClient() + "\n");
            bufferedWriter.write("Total number of input sellers: " + challengeService.getAmountSeller() + "\n");
            bufferedWriter.write("The most expensive sale: " + challengeService.getExpensiveSale() + "\n");
            bufferedWriter.write("The worst seller: " + challengeService.getWorsSeller() + "\n");
            bufferedWriter.close();
        } catch (IOException | NoSuchElementException erro) {
            System.out.printf("Erro:", erro.getMessage());
        }
    }
}
