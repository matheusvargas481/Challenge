package com.matheusvargas481.challenge.dateanalysis.util;

import com.matheusvargas481.challenge.dateanalysis.builder.BuildProcessor;
import com.matheusvargas481.challenge.dateanalysis.domain.Client;
import com.matheusvargas481.challenge.dateanalysis.domain.Sale;
import com.matheusvargas481.challenge.dateanalysis.domain.Salesman;
import com.matheusvargas481.challenge.dateanalysis.service.ChallengeService;

import java.util.List;
import java.util.stream.Collectors;

public class StartSystem {
    public void startDataAnalysis() {
        ReaderFile readerFile = new ReaderFile();
        List<String> lines = readerFile.read();
        ParserDat parserDat = new ParserDat("ç");
        List<String[]> objects = parserDat.createObjects(lines);

        BuildProcessor buildProcessor = new BuildProcessor();

        List<Client> clientList = buildProcessor.filterObjects(objects, Client.TYPE).stream()
                .map(objectClient -> buildProcessor.createClient(objectClient))
                .collect(Collectors.toList());

        List<Salesman> salesmanList = buildProcessor.filterObjects(objects, Salesman.TYPE).stream()
                .map(objectSalesman -> buildProcessor.createSalesman(objectSalesman))
                .collect(Collectors.toList());

        List<Sale> saleList = buildProcessor.filterObjects(objects, Sale.TYPE).stream()
                .map(objectSale -> buildProcessor.createSale(objectSale))
                .collect(Collectors.toList());

        buildProcessor.assignmentSales(saleList, salesmanList);

        ChallengeService challengeService = new ChallengeService(clientList, salesmanList, saleList);

        WriterFile writerFile = new WriterFile(challengeService);
        writerFile.writerFile();
    }
}
