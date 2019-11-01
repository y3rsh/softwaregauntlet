package com.ally.cia.ingestion.sourcefile;

import com.softwareonpurpose.gauntlet.Environment;

import java.io.File;

public class SourceFileProvider {
    private static final String INGESTION_FILE_PATH = Environment.getInstance().getIngestionFilePath();
    private static SourceFileProvider provider;

    public static SourceFileProvider getInstance() {
        if (provider == null) {
            provider = new SourceFileProvider();
        }
        return provider;
    }

    public SourceFile get(int jobId) {
        final String filePath = String.format("%s/jobNumber%d.txt", INGESTION_FILE_PATH, jobId);
        return SourceFile.getInstance(new File(filePath));
    }
}
