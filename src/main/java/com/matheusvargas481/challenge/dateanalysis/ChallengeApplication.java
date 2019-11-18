package com.matheusvargas481.challenge.dateanalysis;

import com.matheusvargas481.challenge.dateanalysis.builder.BuildProcessor;
import com.matheusvargas481.challenge.dateanalysis.domain.Client;
import com.matheusvargas481.challenge.dateanalysis.service.ChallengeService;
import com.matheusvargas481.challenge.dateanalysis.util.ParserDat;
import com.matheusvargas481.challenge.dateanalysis.util.ReaderFile;
import com.matheusvargas481.challenge.dateanalysis.util.WriterFile;

import java.util.List;
import java.util.stream.Collectors;


public class ChallengeApplication {
    public static void main(String[] args) {
        ReaderFile readerFile = new ReaderFile();
        List<String> lines = readerFile.read();

        ParserDat parserDat = new ParserDat("ç");
        List<String[]> objects = parserDat.createObjects(lines);
        BuildProcessor buildProcessor = new BuildProcessor();
        List<Client> clientList = buildProcessor.filterObjects(objects, Client.TYPE).stream()
                .map(objectClient -> buildProcessor.createClient(objectClient))
                .collect(Collectors.toList());



        ChallengeService challengeService = new ChallengeService();
        WriterFile writerFile = new WriterFile(challengeService);
        writerFile.startWriteFile();
    }
}

