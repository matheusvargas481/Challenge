package com.matheusvargas481.challenge.dataanalysis;

import com.matheusvargas481.challenge.dataanalysis.builder.BuildProcessor;
import com.matheusvargas481.challenge.dataanalysis.service.ChallengeService;
import com.matheusvargas481.challenge.dataanalysis.domain.Costumer;
import com.matheusvargas481.challenge.dataanalysis.domain.Sale;
import com.matheusvargas481.challenge.dataanalysis.domain.Salesman;
import com.matheusvargas481.challenge.dataanalysis.util.ParserDat;
import com.matheusvargas481.challenge.dataanalysis.util.ReaderFile;
import com.matheusvargas481.challenge.dataanalysis.util.WriterFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.file.StandardWatchEventKinds.*;


public class DataAnalysis {
    private static final String SEPARETOR = "ç";
    private static ReaderFile readerFile = new ReaderFile();
    private static WatchService watcher;
    private static Map<WatchKey, Path> keys;

    private DataAnalysis(Path dir) throws IOException {
        watcher = FileSystems.getDefault().newWatchService();
        keys = new HashMap<>();
        walkAndRegisterDirectories(dir);
    }

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get(readerFile.getPathIn());
        new DataAnalysis(dir).processEvents();
    }

    private void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
    }

    private void walkAndRegisterDirectories(final Path start) throws IOException {
        readerFile.read();
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void processEvents() {
        for (; ; ) {
            WatchKey key;
            try {
                key = watcher.take();
                startDataAnalysis();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                @SuppressWarnings("rawtypes")
                WatchEvent.Kind kind = event.kind();
                @SuppressWarnings("unchecked")
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                System.out.format("%s: %s\n", event.kind().name(), child);
                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child)) {
                            walkAndRegisterDirectories(child);
                        }
                    } catch (IOException x) {
                        throw new RuntimeException("Process Events failed");
                    }
                }
            }
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }


    public void startDataAnalysis() {
        ParserDat parserDat = new ParserDat(SEPARETOR);
        List<String[]> objects = parserDat.createObjects(readerFile.read());

        BuildProcessor buildProcessor = new BuildProcessor();

        List<Costumer> costumers = buildProcessor.filterObjects(objects, Costumer.TYPE).stream()
                .map(buildProcessor::createCostumer)
                .collect(Collectors.toList());

        List<Salesman> salesmans = buildProcessor.filterObjects(objects, Salesman.TYPE).stream()
                .map(buildProcessor::createSalesman)
                .collect(Collectors.toList());

        List<Sale> sales = buildProcessor.filterObjects(objects, Sale.TYPE).stream()
                .map(buildProcessor::createSale)
                .collect(Collectors.toList());

        buildProcessor.assignmentSales(sales, salesmans);

        ChallengeService challengeService = new ChallengeService(costumers, salesmans, sales);

        WriterFile writerFile = new WriterFile(challengeService);
        writerFile.writerFile();
    }
}