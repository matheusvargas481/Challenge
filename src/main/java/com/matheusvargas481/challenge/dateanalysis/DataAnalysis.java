package com.matheusvargas481.challenge.dateanalysis;

import com.matheusvargas481.challenge.dateanalysis.util.ReaderFile;
import com.matheusvargas481.challenge.dateanalysis.util.StartSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class DataAnalysis {
    private static Map<WatchKey, Path> keyPathMap = new HashMap<>();
    private static ReaderFile readerFile = new ReaderFile();
    private static StartSystem startSystem = new StartSystem();
    private static String homeDir = System.getProperty("user.home");

    public static void main(String[] args) {
        readerFile.read();
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            registerDir(Paths.get(homeDir+"/data"), watchService);
            startListening(watchService);
        } catch (InterruptedException | NoSuchElementException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void registerDir(Path path, WatchService watchService) throws IOException {
        if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            return;
        }
        System.out.println("registering: " + path);
        WatchKey key = path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
        keyPathMap.put(key, path);
        for (File f : path.toFile().listFiles()) {
            registerDir(f.toPath(), watchService);
        }
    }

    private static void startListening(WatchService watchService) throws InterruptedException {
        while (true) {
            WatchKey queuedKey = watchService.take();
            startSystem.startDataAnalysis();
            for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {
                System.out.printf("kind=%s, count=%d, context=%s Context type=%s%n ",
                        watchEvent.kind(),
                        watchEvent.count(), watchEvent.context(),
                        ((Path) watchEvent.context()).getClass());
                if (!queuedKey.reset()) {
                    break;
                }
            }
        }
    }
}