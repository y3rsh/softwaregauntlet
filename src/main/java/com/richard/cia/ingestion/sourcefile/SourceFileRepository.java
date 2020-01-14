package com.richard.cia.ingestion.sourcefile;

import com.softwareonpurpose.gauntlet.Environment;

import java.io.File;

public class SourceFileRepository {
    private static final String INGESTION_FILE_PATH = Environment.getInstance().getIngestionFilePath();
    public static SourceFileRepository getInstance() {
        return new SourceFileRepository();
    }

    SourceFile query(int jobId) {
        final String filePath = String.format("%s/jobNumber%d.txt", INGESTION_FILE_PATH, jobId);
        return SourceFile.getInstance(new File(filePath));
    }
}
