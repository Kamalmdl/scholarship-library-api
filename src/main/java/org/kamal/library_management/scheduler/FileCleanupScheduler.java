package org.kamal.library_management.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileCleanupScheduler {

    @Value("${file.upload-dir}")
    private String uploadDir;


    @Scheduled(cron = "0 0 3 * * *")
    public void cleanUpOrphanFiles() {
        log.info("Starting scheduled cleanup of orphan book cover files at {}", java.time.LocalDateTime.now());

        Path dir = Paths.get(uploadDir);
        int deletedCount = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                if (isOlderThanDays(file, 30)) {
                    Files.deleteIfExists(file);
                    deletedCount++;
                    log.info("Deleted orphan file: {}", file.getFileName());
                }
            }
        } catch (IOException e) {
            log.error("Error during scheduled file cleanup", e);
        }

        log.info("Scheduled cleanup finished. Deleted {} orphan file(s).", deletedCount);
    }

    private boolean isOlderThanDays(Path file, int days) {
        try {
            long modifiedMillis = Files.getLastModifiedTime(file).toMillis();
            long thresholdMillis = System.currentTimeMillis() - (days * 24L * 60 * 60 * 1000);
            return modifiedMillis < thresholdMillis;
        } catch (IOException e) {
            return false;
        }
    }
}